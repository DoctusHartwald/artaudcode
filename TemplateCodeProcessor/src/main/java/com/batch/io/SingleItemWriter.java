package com.batch.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.WriteFailedException;
import org.springframework.batch.item.WriterNotOpenException;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.batch.item.util.ExecutionContextUserSupport;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.batch.velocity.genClass.AbtractFactoryMapper;

public class SingleItemWriter  extends ExecutionContextUserSupport implements ItemWriter<AbtractFactoryMapper> , ResourceAwareItemWriterItemStream<AbtractFactoryMapper>,
InitializingBean  {

	private static final Logger LOG = Logger.getLogger(SingleItemWriter.class);
	private String encoding = OutputState.DEFAULT_CHARSET;
	private OutputState state = null;
	private Resource resource;

	public void open(ExecutionContext executionContext)
	throws ItemStreamException {
		Assert.notNull(resource, "The resource must be set");

		if (!getOutputState().isInitialized()) {
			open(executionContext);
		}
	}
	
	
	private OutputState getOutputState() {
		if (state == null) {
			File file;
			try {
				file = resource.getFile();
			}
			catch (IOException e) {
				throw new ItemStreamException("Could not convert resource to file: [" + resource + "]", e);
			}
			Assert.state(!file.exists() || file.canWrite(), "Resource is not writable: [" + resource + "]");
			state = new OutputState();
			state.setDeleteIfExists(true);
			state.setEncoding(encoding);
		}
		return (OutputState) state;
	}
	
	public void update(ExecutionContext executionContext)
	throws ItemStreamException {
		if (state == null) {
			throw new ItemStreamException("ItemStream not open or already closed.");
		}

		Assert.notNull(executionContext, "ExecutionContext must not be null");
		//TODO to be completed .
	}
	

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void close() throws ItemStreamException {
		if (state != null) {
			try {
					state.outputBufferedWriter.flush();
			}
			catch (IOException e) {
				throw new ItemStreamException("Failed to write footer before closing", e);
			}
			finally {
				state.close();
				if (state.linesWritten == 0 ) {
					try {
						resource.getFile().delete();
					}
					catch (IOException e) {
						throw new ItemStreamException("Failed to delete empty file on close", e);
					}
				}
				state = null;
			}
		}

	}

	

	public void write(AbtractFactoryMapper vAbtractFactoryMapper) throws Exception {

		if (!getOutputState().isInitialized()) {
			throw new WriterNotOpenException("Writer must be open before it can be written to");
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Writing to  file with " + vAbtractFactoryMapper.toString() + " items.");
		}

		OutputState state = getOutputState();

		StringBuilder lines = new StringBuilder();
		int lineCount = 0;
		//	lines.append(abtractFactoryMapper);
		lines.append(vAbtractFactoryMapper.getOutPutStream());
		
		//Writing the output .
		try {
			state.write(lines.toString());
		}
		catch (IOException e) {
			throw new WriteFailedException("Could not write data.  The file may be corrupt.", e);
		}
		state.linesWritten += lineCount;

	}

	public void afterPropertiesSet() throws Exception {	

	}


	public void write(List<? extends AbtractFactoryMapper> items)
			throws Exception {
		
	}

}
