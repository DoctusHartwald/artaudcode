package ccp.batch;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.Properties;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;

import ccp.Confirmation;

public class ConfirmationReader implements ItemReader<Confirmation> {

	static final String NS = "http://www.springsource.com/dining-request"; 

	private Log logger = LogFactory.getLog(getClass());

	private JmsTemplate jmsTemplate;

	private XPathOperations xpathTemplate;

	/**
	 * @param jmsTemplate a @link{JmsTemplate} that must have a finite receiveTimeout 
	 * and a default Destination or destination name 
	 */
	public ConfirmationReader(JmsTemplate jmsTemplate) {
		Assert.isTrue(jmsTemplate.getReceiveTimeout() != JmsTemplate.RECEIVE_TIMEOUT_INDEFINITE_WAIT,
					  "JmsTemplate must have a receive timeout!");
		Assert.isTrue(jmsTemplate.getDefaultDestination() != null || jmsTemplate.getDefaultDestinationName() != null,
				"jmsTemplate must have a defaultDestination or defaultDestinationName!");
		this.jmsTemplate = jmsTemplate;

		Jaxp13XPathTemplate template = new Jaxp13XPathTemplate();
		Properties props = new Properties();
		props.setProperty("ns", NS);
		template.setNamespaces(props);
		this.xpathTemplate = template;
	}

	@Override
	public Confirmation read() throws Exception, UnexpectedInputException, ParseException {
		Message message = jmsTemplate.receive();
		if (message == null) return null;

		String transactionId = extractTransactionId(message);
		return new Confirmation(transactionId);
	}

	private String extractTransactionId(Message message) throws JMSException {
		StreamSource source;
		String xml; // set for populating error message only
		if (message instanceof TextMessage) {
			xml = ((TextMessage) message).getText();
			source = new StreamSource(new StringReader(xml)); 
		} else if (message instanceof BytesMessage) {
			BytesMessage bytesMessage =(BytesMessage) message;
			byte[] bytes = new byte[(int) bytesMessage.getBodyLength()];
			bytesMessage.readBytes(bytes);
			source = new StreamSource(new ByteArrayInputStream(bytes));
			xml = new String(bytes);
		} else {
			throw new ParseException("Don't know how to process a " + ClassUtils.getShortName(message.getClass()));
		}

		String transactionId = xpathTemplate.evaluateAsString("/ns:reward-confirmation/@dining-transaction-id", source);
		if ("".equals(transactionId)) {
			throw new ParseException("Can't find root reward-confirmation with a dining-transaction-id attribute in this xml:\n" + xml);
		}
		logger.debug("Read confirmation message with transaction-id " + transactionId);
		return transactionId;
	}

}
