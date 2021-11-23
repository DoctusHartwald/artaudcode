package com.myshop.integration.processor.supplier1;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.myshop.data.Item;

public class Supplier1CatalogNormalizer implements Processor {

	public void process(Exchange exchange) throws Exception {
		List<Supplier1Item> inItems = exchange.getIn().getBody(List.class);
		
		List<Item> outItems = new ArrayList<Item>();
		for (Supplier1Item supplier1Item : inItems) {
			Item item = new Item();
			item.setSupplierId(supplier1Item.getSupplierId());
			item.setName(supplier1Item.getName());
			item.setPrice(supplier1Item.getPrice());
			outItems.add(item);
		}
		
		exchange.getOut().setBody(outItems);
	}

}
