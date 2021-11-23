package com.myshop.customerclient.io;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.myshop.address.AddressConstants;
import com.myshop.data.Order;

public class OrderDispatcher {

	private static final String DEFAULT_USER = ActiveMQConnection.DEFAULT_USER;
	private static final String DEFAULT_PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
	
	private Connection connection;
	private Session session;
	private Destination orderQueue;
	private MessageProducer producer;
	
	/**
	 * Creates a new OrderDispatcherException using the default login and password.
	 * 
	 * @param url the URL of the ActiveMQ server.
	 * @throws OrderDispatcherException if any error occurs during the connection.
	 */
	public OrderDispatcher(String url) throws OrderDispatcherException {
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
	public OrderDispatcher(String url, String user, String password) throws OrderDispatcherException {
		ActiveMQConnectionFactory connnectionFactory = 
			new ActiveMQConnectionFactory(user, password, url);
		try {
			connection = connnectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			orderQueue = session.createQueue(AddressConstants.ORDER_QUEUE_NAME);
			producer = session.createProducer(orderQueue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//producer.setTimeToLive(10000);
		} catch (JMSException e) {
			throw new OrderDispatcherException(e);
		}
	}
	
	/**
	 * Sends the order to the message broker.
	 * 
	 * @param order the order to send.
	 */
	public void send(Order order) throws OrderDispatcherException {
		try {
			ObjectMessage message = session.createObjectMessage();
			message.setObject(order);
			producer.send(message);
		} catch (JMSException e) {
			throw new OrderDispatcherException(e);
		}
	}
}
