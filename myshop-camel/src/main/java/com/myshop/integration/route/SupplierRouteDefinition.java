package com.myshop.integration.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.impl.DefaultCamelContext;

import com.myshop.integration.processor.UnchangedItemDropper;
import com.myshop.integration.processor.supplier1.Supplier1CatalogXMLToBeanProcessor;

public class SupplierRouteDefinition extends RouteBuilder {
	private CamelContext context;
	public SupplierRouteDefinition(){
	
	}
	@Override
	public void configure() throws Exception {
		from("timer://foo?fixedRate=true&delay=0&period=5000")
	    .to("http://artaud1.hopto.org/Camel/supplier2.xml")
		//.process(new Supplier1CatalogXMLToBeanProcessor())
		//.process(new UnchangedItemDropper("jdbc:mysql://localhost:3306/test"))
		.to("activemq:queue:NEW_PRODUCTS");
		
		from("activemq:queue:NEW_PRODUCTS")
		.convertBodyTo(String.class)
		.to("stream:out");
		
		from("timer://foo?fixedRate=true&delay=0&period=10000")
	    .to("http://artaud1.hopto.org/Camel/supplier2.xml")
	    //.setHeader(FileComponent.HEADER_FILE_NAME, "message.html").to("file:target/google")
	    .convertBodyTo(String.class)
	    .to("stream:out");
	}
	public void camelContext(){
		context = new DefaultCamelContext();
		context.addComponent(
				"activemq", 
				ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
		try {
			context.addRoutes(new SupplierRouteDefinition());
			context.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SupplierRouteDefinition supplier = new SupplierRouteDefinition();
		supplier.camelContext();
	}

}
