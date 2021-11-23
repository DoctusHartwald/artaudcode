package ccp.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jms.core.JmsTemplate;

import ccp.Dining;

public class RequestSender implements ItemWriter<Dining> {
	private Log logger = LogFactory.getLog(getClass());
	private JmsTemplate jmsTemplate;

	public RequestSender(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void write(List<? extends Dining> items) throws Exception {
		logger.debug("Sending requests for " + items.size() + " Dinings");
		// TODO XX: send a message for each Dining in items; you can rely
		// on the MessageConverter of the jmsTemplate to convert the Dining for you
		throw new UnsupportedOperationException("Please implement TODO XX!");
	}
}
