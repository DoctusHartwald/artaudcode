package com.myshop.customerclient.io;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.myshop.address.AddressConstants;
import com.myshop.data.Order;

public class OrderConsumer {

	private static final String DEFAULT_USER = ActiveMQConnection.DEFAULT_USER;
	private static final String DEFAULT_PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
	
	private Connection connection;
	private Session session;
	private Destination orderQueue;
	private MessageConsumer consumer;
	
	/**
	 * Creates a new OrderDispatcherException using the default login and password.
	 * 
	 * @param url the URL of the ActiveMQ server.
	 * @throws OrderDispatcherException if any error occurs during the connection.
	 */
	public OrderConsumer(String url) throws OrderDispatcherException {
		this(url, DEFAULT_USER, DEFAULT_PASSWORD);
	}
	
	/**
	 * Creates a new OrderDispatcherException using the given login and password.
	 * 
	 * @param url the URL of the ActiveMQ server.
	 * @param user the login to use for ActiveMQ connection.
	 * @param password the password to use for ActiveMQ connection.
	 * @throws OrderDispatcherException if any error occurs during the connection.
	 */
	public OrderConsumer(String url, String user, String password) throws OrderDispatcherException {
		ActiveMQConnectionFactory connnectionFactory = 
			new ActiveMQConnectionFactory(user, password, url);
		try {
			connection = connnectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			orderQueue = session.createQueue(AddressConstants.ORDER_QUEUE_NAME);
			consumer = session.createConsumer(orderQueue);
		} catch (JMSException e) {
			throw new OrderDispatcherException(e);
		}
	}
	
	/**
	 * Sends the order to the message broker.
	 * 
	 * @param order the order to send.
	 */
	public Order recv() throws OrderDispatcherException {
		try {
			ObjectMessage message = (ObjectMessage)consumer.receive();
			return (Order)message.getObject();
		} catch (JMSException e) {
			throw new OrderDispatcherException(e);
		}
	}
	
	public static void main(String[] args) {
		try {
			OrderConsumer consumer = new OrderConsumer("tcp://192.168.0.8:61616");
			System.out.println(consumer.recv().getName());
		} catch (OrderDispatcherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
