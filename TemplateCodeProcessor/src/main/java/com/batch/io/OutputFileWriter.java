package com.batch.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.batch.velocity.genClass.AbtractFactoryMapper;

@Service("outputWriter")
public class OutputFileWriter   implements ItemWriter<AbtractFactoryMapper> {
	
	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}



	Resource resource;
	StepExecution stepExecution;


	


	public void setResource( final Resource resource )
	{
		this.resource = resource;
		
	}



	public void write(List<? extends AbtractFactoryMapper> items)
			throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(getResource().getFilename()));
		for (AbtractFactoryMapper abtractFactoryMapper : items) {
			writer.write(abtractFactoryMapper.getOutPutStream());
		}
		
		writer.flush();
		writer.close();
		
	}
	
}
