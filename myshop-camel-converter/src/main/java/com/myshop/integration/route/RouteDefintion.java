package com.myshop.integration.route;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import com.myshop.beans.Order;
import com.myshop.converter.IdConverterProcessor;


public class RouteDefintion extends RouteBuilder  {
	private CamelContext context;
	
	public RouteDefintion(){
		
	}
	
	/**
	 * Configuration des flux gerer par Apache CAMEL.
	 * whether done with full Java or Spring Container.
	 * @author artaud
	 */
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void camelContext(){
		context = new DefaultCamelContext();
		//context.addComponent("activemq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
		try {
			context.addRoutes(new RouteDefintion());
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		RouteDefintion supplier = new RouteDefintion();
		supplier.camelContext();
		
		Order vOrder =new Order();
		vOrder.setId(1);
		vOrder.setName("Sony Telephone");
		vOrder.setPrice(new Float("12.6"));
		
		try {
			String vResu = IdConverterProcessor.toIntString(vOrder.getId());
			System.out.println(vResu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}




}
