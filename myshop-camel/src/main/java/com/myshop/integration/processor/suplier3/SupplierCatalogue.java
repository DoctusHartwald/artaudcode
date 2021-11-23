package com.myshop.integration.processor.suplier3;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.http.HttpProducer;

public class SupplierCatalogue implements Processor{
	private ProducerTemplate template ; 

	public void process(Exchange exchange) throws Exception {
		 //exchange = template.send("http://www.google.com/search", exchange.getIn().setHeader(HttpProducer.QUERY, "hl=en&q=activemq");
		Message message = exchange.getIn();
		exchange.getOut().setBody(message);

	}

}
