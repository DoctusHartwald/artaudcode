package rewards.messaging;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.junit.Before;
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

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class RewardMessagingIntegrationTests {

	@Autowired @Qualifier("xmlDinings")
	MessageChannel xmlDinings;

	@Autowired @Qualifier("xmlConfirmations")
	PollableChannel xmlConfirmations;

	private Source diningSource;

	@Before
	public void setup() throws Exception {
		diningSource = new DOMSource(DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(
						new ClassPathResource("dining-sample.xml", this
								.getClass()).getFile()));
	}

	@Test
	public void sendDining() throws Exception {
		xmlDinings.send(MessageBuilder.withPayload(diningSource).build());
		Message<?> received = xmlConfirmations.receive(100000);
		assertNotNull(received);
		Object payload = received.getPayload();
		assertThat(payload, is(String.class));
		assertTrue(((String)payload).matches(".*<reward-confirmation.*"));
	}

	@Test
	public void configOk() throws Exception {
		// testing the fixture
	}
}
