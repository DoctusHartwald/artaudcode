package rewards.messaging;

import static org.junit.Assert.assertNotNull;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class RewardMessagingIntegrationTests {

	@Autowired
	MessageChannel mixedXmlDinings;

	@Autowired
	PollableChannel xmlConfirmations;


	private Document singleDiningXml;
	private Document multipleDiningsXml;

	@Before
	public void setup() throws Exception {
		singleDiningXml = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(
						new ClassPathResource("dining-sample.xml", this
								.getClass()).getFile());
		multipleDiningsXml = DocumentBuilderFactory.newInstance()
		.newDocumentBuilder().parse(
				new ClassPathResource("dinings-sample.xml", this
						.getClass()).getFile());
	}

	@Test
	public void sendSingleDining() throws Exception {
		mixedXmlDinings.send(MessageBuilder.withPayload(singleDiningXml).build());
		Message<?> received = xmlConfirmations.receive(1000);
		assertNotNull(received);
	}

	@Test
	public void sendMultipleDinings() throws Exception {
		mixedXmlDinings.send(MessageBuilder.withPayload(multipleDiningsXml).build());
		assertNotNull(xmlConfirmations.receive(1000));
		assertNotNull(xmlConfirmations.receive(1000));
	}
	
	@Test
	public void configOk() throws Exception {
		// test the fixture
	}
}
