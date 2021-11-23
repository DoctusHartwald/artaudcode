package rewards.messaging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rewards.Dining;
import rewards.RewardConfirmation;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MarshallingIntegrationTests {

	// Inbound path
	@Autowired
	@Qualifier("xmlDinings")
	private MessageChannel xmlDinings;

	@Autowired
	@Qualifier("dinings")
	private PollableChannel dinings;

	// Outbound path
	@Autowired
	@Qualifier("xmlConfirmations")
	private PollableChannel xmlConfirmations;

	@Autowired
	@Qualifier("confirmations")
	private MessageChannel confirmations;

	@Test
	public void configOk() throws Exception {
		//
	}

	@Test
	public void inboundDiningXml() throws Exception {
		Source diningSource = new DOMSource(DocumentBuilderFactory
				.newInstance().newDocumentBuilder().parse(
						new ClassPathResource("dining-sample.xml", this
								.getClass()).getFile()));
		xmlDinings.send(MessageBuilder.withPayload(diningSource).build());
		assertThat(((Dining) dinings.receive().getPayload()).getTransactionId(),
				is("universallyUniqueString"));
	}
	
	@Test
	public void outboundConfirmation() throws Exception {
		RewardConfirmation confirmation = mock(RewardConfirmation.class);
		when(confirmation.getDiningTransactionId()).thenReturn("UUID");
		confirmations.send(MessageBuilder.withPayload(confirmation).build());
		Message<?> receivedMessage = xmlConfirmations.receive(1000);
		assertThat(receivedMessage.getPayload(), is(String.class));
		String payload = (String) receivedMessage.getPayload();
		assertTrue(payload.matches(".*dining-transaction-id=\"UUID\".*"));
	}

}
