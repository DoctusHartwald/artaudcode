package rewards.messaging.client;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;

/**
 * Tests the Dining batch processor
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionalDiningProcessorTests {

	@Autowired
	private DiningProcessor diningProcessor;

	@Autowired
	private RewardConfirmationLogger confirmationLogger;

	@Autowired
	private RewardNetwork rewardNetwork;

	private RewardConfirmation confirmation = new RewardConfirmation(
			"1234324234", new AccountContribution("12341", null, null));

	@Test
	public void testBatch() throws Exception {
		Dining dining1 = Dining.createDining("80.93", "1234123412341234",
				"1234567890");
		Dining dining2 = Dining.createDining("56.12", "125699999999999",
				"1234567890");

		// Record iterative behavior for rewardNetwork mock
		when(rewardNetwork.rewardAccountFor(isA(Dining.class)))
				// TODO 01: uncomment next line to simulate a failed business operation
				 .thenThrow(new RuntimeException("expected"))
				// Throw Exception the first time, but
				// return normally for consecutive calls
				.thenReturn(confirmation)
				.thenReturn(confirmation)//no stack trace error generate by Mockito 
				;

		// send both dinings to the queue
		diningProcessor.process(dining1);
		diningProcessor.process(dining2);

		waitForBatch(2, 1000);
		assertEquals(2, confirmationLogger.getConfirmations().size());
	}

	private void waitForBatch(int batchSize, int timeout)
			throws InterruptedException {
		int sleepTime = 100;
		while (confirmationLogger.getConfirmations().size() < batchSize
				&& timeout > 0) {
			Thread.sleep(sleepTime);
			timeout -= sleepTime;
		}
	}
}
