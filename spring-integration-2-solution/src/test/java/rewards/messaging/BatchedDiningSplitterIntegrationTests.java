package rewards.messaging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.xml.parsers.DocumentBuilderFactory;
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
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class BatchedDiningSplitterIntegrationTests {

	// Inbound path
	@Autowired
	@Qualifier("mixedXmlDinings")
	private MessageChannel mixedXmlDinings;

	@Autowired
	@Qualifier("xmlDinings")
	private PollableChannel xmlDinings;

	private XPathOperations xpathTemplate = new Jaxp13XPathTemplate();

	@Test
	public void configOk() throws Exception {
		//
	}

	@Test
	public void inboundSingleDiningXml() throws Exception {
		Document diningNode = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(
						new ClassPathResource("dining-sample.xml", this
								.getClass()).getFile());
		mixedXmlDinings.send(MessageBuilder.withPayload(diningNode).build());
		Message<?> receivedMessage = xmlDinings.receive();
		assertThat(receivedMessage.getPayload(), is(Node.class));
		Document payload = (Document) receivedMessage.getPayload();
		assertThat(xpathTemplate.evaluateAsString("/dining/@transaction-id",
				new DOMSource(payload)), is("universallyUniqueString"));
	}

	@Test(timeout = 2000)
	public void inboundMultipleDiningXml() throws Exception {
		Document diningDocument = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(
						new ClassPathResource("dinings-sample.xml", this
								.getClass()).getFile());

		mixedXmlDinings
				.send(MessageBuilder.withPayload(diningDocument).build());
		Message<?> receivedMessage = xmlDinings.receive();
		assertThat(receivedMessage.getPayload(), is(Document.class));
		Document payload = (Document) receivedMessage.getPayload();
		assertThat(xpathTemplate.evaluateAsString(
				"/dining/@transaction-id", new DOMSource(payload)),
				is("universallyUniqueString1"));
		assertThat(xpathTemplate.evaluateAsString(
				"/dining/@transaction-id", new DOMSource(
						(Node) xmlDinings.receive().getPayload())),
				is("universallyUniqueString2"));
	}
}
