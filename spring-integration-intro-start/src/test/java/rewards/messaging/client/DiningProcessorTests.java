package rewards.messaging.client;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rewards.Dining;

/**
 * Tests the Dining batch processor
 */
// TODO 04: remove all JMS specific config files and load
// spring-integration-rewards-config.xml instead
@ContextConfiguration(locations = {
		"/rewards/messaging/spring-integration-rewards-config.xml",
"/system-test-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DiningProcessorTests {

	@Autowired
	private DiningProcessor diningBatchProcessor;

	// TODO 02: replace this with an autowired PollableChannel
	//@Autowired
	//private RewardConfirmationLogger confirmationLogger;

	@Autowired
	private PollableChannel confirmationChannel;

	@Test
	public void testBatch() throws Exception {
		Dining dining1 = Dining.createDining("80.93", "1234123412341234", "1234567890");
		Dining dining2 = Dining.createDining("56.12", "1234123412341234", "1234567890");
		Dining dining3 = Dining.createDining("32.64", "1234123412341234", "1234567890");
		Dining dining4 = Dining.createDining("77.05", "1234123412341234", "1234567890");
		Dining dining5 = Dining.createDining("94.50", "1234123412341234", "1234567890");

		diningBatchProcessor.process(dining1);
		diningBatchProcessor.process(dining2);
		diningBatchProcessor.process(dining3);
		diningBatchProcessor.process(dining4);
		diningBatchProcessor.process(dining5);

		// TODO 03: use getMessagesFrom(confirmationChannel) to complete the assertion
		// getMessagesFrom(confirmationChannel)
		//waitForBatch(5, 1000);
		//assertEquals(5, confirmationLogger.getConfirmations().size());
		assertThat(getMessagesFrom(confirmationChannel).size(), is(5));

	}


	private List<Message<?>> getMessagesFrom(PollableChannel confirmationChannel) {
		List<Message<?>> messages = new ArrayList<Message<?>>();
		Message<?> message;
		while ((message = confirmationChannel.receive(500)) != null) {
			messages.add(message);
		}
		return messages;
	}


	// TODO 01: delete this method and calls to it
	//	private void waitForBatch(int batchSize, int timeout)
	//			throws InterruptedException {
	//		int sleepTime = 100;
	//		while (confirmationLogger.getConfirmations().size() < batchSize
	//				&& timeout > 0) {
	//			Thread.sleep(sleepTime);
	//			timeout -= sleepTime;
	//		}
	//	}


}
