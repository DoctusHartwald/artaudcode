package com.batch.factory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.stereotype.Component;

@Component("ledgerProcessor")
public class LedgerProcessor implements ItemProcessor<FlatFileItemReader, StaxEventItemWriter> {

	public StaxEventItemWriter process(FlatFileItemReader item) throws Exception {
		
        return new StaxEventItemWriter();
	}
	
}
