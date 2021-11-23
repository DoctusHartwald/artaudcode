package ccp.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import javax.jms.BytesMessage;
import javax.jms.MapMessage;
import javax.jms.TextMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.batch.item.ParseException;
import org.springframework.jms.core.JmsTemplate;

import ccp.Confirmation;

public class ConfirmationReaderTests {

	private JmsTemplate jmsTemplate;
	private ConfirmationReader confirmationReader;

	private static final String UU_ID = UUID.randomUUID().toString();
	private static final String VALID_XML = "<?xml version=\"1.0\"?>\n" 
		+ "<reward-confirmation xmlns=\"" + ConfirmationReader.NS + "\" dining-transaction-id=\"" + UU_ID + "\"/>";

	@Before
	public void setup() {
		jmsTemplate = mock(JmsTemplate.class);
		when(jmsTemplate.getReceiveTimeout()).thenReturn(JmsTemplate.RECEIVE_TIMEOUT_NO_WAIT);
		when(jmsTemplate.getDefaultDestinationName()).thenReturn("someDestination");
		confirmationReader = new ConfirmationReader(jmsTemplate);
	}

	@Test
	public void processValidStringMessage() throws Exception {
		TextMessage textMessage = mock(TextMessage.class);
		when(jmsTemplate.receive()).thenReturn(textMessage);
		when(textMessage.getText()).thenReturn(VALID_XML);

		Confirmation confirmation = confirmationReader.read();
		assertEquals(UU_ID, confirmation.getTransactionId());
	}

	@Test
	public void processValidBytesMessage() throws Exception {
		BytesMessage bytesMessage = mock(BytesMessage.class);
		when(jmsTemplate.receive()).thenReturn(bytesMessage);
		final int length = VALID_XML.getBytes().length;
		when(bytesMessage.getBodyLength()).thenReturn((long) length);
		when(bytesMessage.readBytes((byte[]) anyObject())).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				System.arraycopy(VALID_XML.getBytes(), 0, invocation.getArguments()[0], 0, length);
				return length;
			}
		});

		Confirmation confirmation = confirmationReader.read();
		assertEquals(UU_ID, confirmation.getTransactionId());
	}

	@Test
	public void processEmptyQueue() throws Exception {
		when(jmsTemplate.receive()).thenReturn(null);
		
		assertNull(confirmationReader.read());
	}

	@Test(expected=ParseException.class)
	public void processInvalidStringMessage() throws Exception {
		TextMessage textMessage = mock(TextMessage.class);
		when(jmsTemplate.receive()).thenReturn(textMessage);
		when(textMessage.getText()).thenReturn("<not-what-we-expected/>");
		
		confirmationReader.read();
	}

	@Test(expected=ParseException.class)
	public void processMapMessage() throws Exception {
		MapMessage mapMessage = mock(MapMessage.class);
		when(jmsTemplate.receive()).thenReturn(mapMessage);
		
		confirmationReader.read();
	}

}
