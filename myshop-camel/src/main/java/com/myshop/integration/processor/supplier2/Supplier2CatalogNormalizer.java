package com.myshop.integration.processor.supplier2;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.myshop.data.Item;

public class Supplier2CatalogNormalizer implements Processor {

	public void process(Exchange exchange) throws Exception {
		List<Supplier2Item> inItems = exchange.getIn().getBody(List.class);
		
		List<Item> outItems = new ArrayList<Item>();
		for (Supplier2Item supplier2Item : inItems) {
			Item item = new Item();
			item.setSupplierId(supplier2Item.getSupplierId());
			item.setName(supplier2Item.getName());
			item.setPrice(supplier2Item.getPrice());
			outItems.add(item);
		}
		
		exchange.getOut().setBody(outItems);
	}

}
