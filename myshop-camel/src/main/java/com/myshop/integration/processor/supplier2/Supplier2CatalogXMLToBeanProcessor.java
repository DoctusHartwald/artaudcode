package com.myshop.integration.processor.supplier2;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.myshop.data.Item;

public class Supplier2CatalogXMLToBeanProcessor implements Processor {

	private final Unmarshaller unmarshaller;
	
	public Supplier2CatalogXMLToBeanProcessor() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public void process(Exchange exchange) throws Exception {
		Message message = exchange.getIn();
		String xmlContent = message.getBody(String.class);
		InputStream inputStream = new StringBufferInputStream(xmlContent);
		
		Items items = (Items) unmarshaller.unmarshal(inputStream);
		
		exchange.getOut().setBody(items.getItems());
	}
	
	@XmlRootElement(name = "products")
	@XmlAccessorType(XmlAccessType.FIELD)
	private static class Items {
		
		@XmlElement(name = "product")
		private List<Supplier2Item> items;

		public List<Supplier2Item> getItems() {
			return items;
		}

		public void setItems(List<Supplier2Item> items) {
			this.items = items;
		}
		
		
	}
}
