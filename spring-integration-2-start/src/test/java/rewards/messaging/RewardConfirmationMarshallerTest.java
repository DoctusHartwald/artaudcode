package rewards.messaging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;

import rewards.RewardConfirmation;

public class RewardConfirmationMarshallerTest {

	private RewardConfirmationMarshaller marshaller = new RewardConfirmationMarshaller();

	private XPathOperations xpathTemplate = new Jaxp13XPathTemplate();

	@Test
	public void marshallDOM() throws Exception {
		RewardConfirmation rewardConfirmation = mock(RewardConfirmation.class);
		when(rewardConfirmation.getDiningTransactionId()).thenReturn("w00t");
		DOMResult result = new DOMResult();
		marshaller.marshal(rewardConfirmation, result);
		DOMSource source = new DOMSource(result.getNode());
		assertThat(xpathTemplate.evaluateAsString(
				"/reward-confirmation/@dining-transaction-id", source),
				is("w00t"));
	}

	@Test
	public void marshallStream() throws Exception {
		RewardConfirmation rewardConfirmation = mock(RewardConfirmation.class);
		when(rewardConfirmation.getDiningTransactionId()).thenReturn("w00t");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		marshaller.marshal(rewardConfirmation, result);
		Source source = new StreamSource(new StringReader(writer.getBuffer()
				.toString()));
		assertThat(xpathTemplate.evaluateAsString(
				"/reward-confirmation/@dining-transaction-id", source),
				is("w00t"));
	}

	@Test
	public void marshallSAX() throws Exception {
		RewardConfirmation rewardConfirmation = mock(RewardConfirmation.class);
		when(rewardConfirmation.getDiningTransactionId()).thenReturn("w00t");
		ContentHandler handler = mock(ContentHandler.class);
		SAXResult result = new SAXResult(handler);
		marshaller.marshal(rewardConfirmation, result);
		verify(handler).startDocument();
		verify(handler).startElement(isA(String.class),
				eq("reward-confirmation"), isA(String.class),
				diningTransactionIs("w00t"));
		verify(handler).endDocument();
	}

	private Attributes diningTransactionIs(final String string) {
		return argThat(new BaseMatcher<Attributes>(){
			public boolean matches(Object attributes) {
				return ((Attributes)attributes).getValue("dining-transaction-id") .equals(string);
			}
			public void describeTo(Description description) {
				description.appendText("dining-transaction-id=");
				description.appendValue(string);
			}});
	}
}
