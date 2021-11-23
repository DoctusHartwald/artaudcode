package com.myshop.integration.processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.myshop.data.Item;

public class UnchangedItemDropper implements Processor {

	private final PreparedStatement statement;
	
	public UnchangedItemDropper(String url) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, "root", "");
		statement = connection.prepareStatement("select supplierId from product");
	}
	public void TypeSupported(ResultSet types) throws SQLException{
		System.out.println("###################################");
		   for(int i=0; i<types.getMetaData().getColumnCount();i++){
		      String nomColonne = types.getMetaData().getColumnName(i+1);
		      Object valeurColonne = types.getObject(i+1);
		      System.out.println(nomColonne+" = "+valeurColonne);
		   }

	}
	public void process(Exchange exchange) throws Exception {
		Message message = exchange.getIn();
		List<Item> inItems = message.getBody(List.class);
		
		ResultSet results = statement.executeQuery();
		Set<Long> ids = new HashSet<Long>();
		while(results.next()) {
			//TypeSupported(results);
			ids.add((long)results.getInt(1));
		}
		
		List<Item> outItems = new ArrayList<Item>();
		for (Item item : inItems) {
			if (!ids.contains(item.getSupplierId())) {
				outItems.add(item);
			}
		}
		
		exchange.getOut().setBody(outItems);
	}

}
