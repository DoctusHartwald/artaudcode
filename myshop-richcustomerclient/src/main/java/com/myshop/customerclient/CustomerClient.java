package com.myshop.customerclient;

import com.myshop.address.AddressConstants;
import com.myshop.customerclient.io.OrderDispatcher;
import com.myshop.customerclient.io.OrderDispatcherException;
import com.myshop.data.Order;

public class CustomerClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OrderDispatcher dispatcher = new OrderDispatcher("tcp://192.168.0.8:61616");
			Order order = new Order();
			order.setName("test1");
			dispatcher.send(order);
			System.out.println("order sent.");
		} catch (OrderDispatcherException e) {
			e.printStackTrace();
		}
	}

}
