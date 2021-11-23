package rewards.messaging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.xml.transform.StringSource;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;


@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class InboundFileDiningIntegrationTests {

	@Autowired
	private PollableChannel xmlConfirmations;

	private XPathOperations xpathTemplate = new Jaxp13XPathTemplate();

	@Test
	@Ignore
	public void filesReceived() throws Exception {
		String xpath = "/reward-confirmation/@dining-transaction-id";
		int messageCount = 0;
		for(;;) {
			Message<?> receivedMessage = xmlConfirmations.receive(2500);
			if (receivedMessage == null) {
				break;
			}
			messageCount++;
			assertThat(receivedMessage.getPayload(), is(String.class));
			String payload = (String) receivedMessage.getPayload();
			String diningTxId =  xpathTemplate.evaluateAsString(xpath, new StringSource(payload));
			assertTrue(diningTxId.startsWith("universallyUniqueString"));
		}
		assertEquals(3, messageCount);
	}

	@Test
	public void configOk() throws Exception {
		//
	}

}
