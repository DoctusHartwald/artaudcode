package com.batch.velocity.genClass;

import java.io.BufferedWriter;

public abstract class AbtractFactoryMapper  {
	private String outPutStream;

	/**
	 * @return the outPutStream
	 */
	public String getOutPutStream() {
		return outPutStream;
	}

	/**
	 * @param writer the outPutStream to set
	 */
	public void setOutPutStream(String writer) {
		this.outPutStream = writer;
	}
	
}
