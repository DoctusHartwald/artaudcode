package com.batch.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.UnsupportedCharsetException;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.batch.support.transaction.TransactionAwareBufferedWriter;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * Encapsulates the runtime state of the writer. All state changing
 * operations on the writer go through this class.
 */
public class OutputState {
	
	Resource resource;
	FileOutputStream os;

	// The bufferedWriter over the file channel that is actually written
	Writer outputBufferedWriter;
	
	// output file
	String encoding = DEFAULT_CHARSET;
	// default encoding for writing to output files - set to UTF-8.
	static final String DEFAULT_CHARSET = "UTF-8";
	static final boolean DEFAULT_TRANSACTIONAL =true;

	private boolean transactional = DEFAULT_TRANSACTIONAL;


	FileChannel fileChannel;

	// this represents the charset encoding (if any is needed) for the


	boolean restarted = false;

	long lastMarkedByteOffsetPosition = 0;

	long linesWritten = 0;

	boolean shouldDeleteIfExists = true;

	boolean initialized = true;

	static final String RESTART_DATA_NAME = "current.count";

	/**
	 * Return the byte offset position of the cursor in the output file as a
	 * long integer.
	 */
	public long position() throws IOException {
		long pos = 0;

		if (fileChannel == null) {
			return 0;
		}

		outputBufferedWriter.flush();
		pos = fileChannel.position();
		if (transactional) {
			pos += ((TransactionAwareBufferedWriter) outputBufferedWriter).getBufferSize();
		}

		return pos;

	}

	/**
	 * @param executionContext
	 */
	public void restoreFrom(ExecutionContext executionContext) {
		//lastMarkedByteOffsetPosition = executionContext.getLong(getKey(RESTART_DATA_NAME));
		restarted = true;
	}

	/**
	 * @param shouldDeleteIfExists
	 */
	public void setDeleteIfExists(boolean shouldDeleteIfExists) {
		this.shouldDeleteIfExists = shouldDeleteIfExists;
	}

	/**
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Close the open resource and reset counters.
	 */
	public void close() {

		initialized = false;
		restarted = false;
		try {
			if (outputBufferedWriter != null) {
				outputBufferedWriter.close();
			}
		}
		catch (IOException ioe) {
			throw new ItemStreamException("Unable to close the the ItemWriter", ioe);
		}
		finally {
			if (!transactional) {
				closeStream();
			}
		}
	}

	private void closeStream() {
		try {
			if (fileChannel != null) {
				fileChannel.close();
			}
		}
		catch (IOException ioe) {
			throw new ItemStreamException("Unable to close the the ItemWriter", ioe);
		}
		finally {
			try {
				if (os != null) {
					os.close();
				}
			}
			catch (IOException ioe) {
				throw new ItemStreamException("Unable to close the the ItemWriter", ioe);
			}
		}
	}

	/**
	 * @param line
	 * @throws IOException
	 */
	public void write(String line) throws IOException {
		if (!initialized) {
			initializeBufferedWriter();
		}

		outputBufferedWriter.write(line);
		outputBufferedWriter.flush();
	}

	/**
	 * Truncate the output at the last known good point.
	 * 
	 * @throws IOException
	 */
	public void truncate() throws IOException {
		fileChannel.truncate(lastMarkedByteOffsetPosition);
		fileChannel.position(lastMarkedByteOffsetPosition);
	}

	/**
	 * Creates the buffered writer for the output file channel based on
	 * configuration information.
	 * @throws IOException
	 */
	private void initializeBufferedWriter() throws IOException {

		File file = resource.getFile();

		FileUtils.setUpOutputFile(file, restarted, shouldDeleteIfExists);

		os = new FileOutputStream(file.getAbsolutePath(), true);
		fileChannel = os.getChannel();

		outputBufferedWriter = getBufferedWriter(fileChannel, encoding);

		Assert.state(outputBufferedWriter != null);
		// in case of restarting reset position to last committed point
		if (restarted) {
			checkFileSize();
			truncate();
		}

		initialized = true;
		linesWritten = 0;
	}

	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Returns the buffered writer opened to the beginning of the file
	 * specified by the absolute path name contained in absoluteFileName.
	 */
	private Writer getBufferedWriter(FileChannel fileChannel, String encoding) {
		try {
			Writer writer = Channels.newWriter(fileChannel, encoding);
			if (transactional) {
				return new TransactionAwareBufferedWriter(writer, new Runnable() {
					public void run() {
						closeStream();
					}
				});
			} else {
				return new BufferedWriter(writer);
			}
		}
		catch (UnsupportedCharsetException ucse) {
			throw new ItemStreamException("Bad encoding configuration for output file " + fileChannel, ucse);
		}
	}

	/**
	 * Checks (on setState) to make sure that the current output file's size
	 * is not smaller than the last saved commit point. If it is, then the
	 * file has been damaged in some way and whole task must be started over
	 * again from the beginning.
	 * @throws IOException if there is an IO problem
	 */
	private void checkFileSize() throws IOException {
		long size = -1;

		outputBufferedWriter.flush();
		size = fileChannel.size();

		if (size < lastMarkedByteOffsetPosition) {
			throw new ItemStreamException("Current file size is smaller than size at last commit");
		}
	}

}
