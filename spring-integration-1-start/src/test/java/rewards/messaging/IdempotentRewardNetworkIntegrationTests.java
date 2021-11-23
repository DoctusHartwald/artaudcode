package rewards.messaging;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.reward.RewardRepository;


@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class IdempotentRewardNetworkIntegrationTests {

	@Autowired
	private RewardRepository rewardRepository;

	@Autowired
	private RewardNetwork delegate;
	private Dining dining = mock(Dining.class);

	@Autowired
	DiningProcessor inputGateway;

	@Autowired
	PollableChannel confirmations;

	@Test
	public void configOk() throws Exception {
		//
	}

	@Test
	public void idempotence() throws Exception {
		// A mocked confirmation
		RewardConfirmation confirmation = mock(RewardConfirmation.class);

		// First dining is sent to the Reward Network
		when(delegate.rewardAccountFor(dining)).thenReturn(confirmation);
		inputGateway.process(dining);
		RewardConfirmation firstConfirmation = (RewardConfirmation) confirmations
				.receive(1000).getPayload();

		// Second dining is sent to the Reward Network
		when(rewardRepository.findConfirmationFor(dining)).thenReturn(
				firstConfirmation);
		inputGateway.process(dining);
		RewardConfirmation secondConfirmation = (RewardConfirmation) confirmations
				.receive(1000).getPayload();

		assertNotNull(secondConfirmation);

		// TODO 1a: assert that the first and second confirmation are the same and
		// TODO 1b: verify rewardAccountFor is invoked only once (instead of twice)
		verify(delegate, times(2)).rewardAccountFor(dining);
	}

	/**
	 * There is a problem with using certain types of mocks in Spring contexts
	 * which is fixed in Spring 3.0, however Spring Integration doesn't have a
	 * milestone out building on top of Spring 3.0 yet. Don't worry about this,
	 * it is not important for understanding the concepts in the lab.
	 * 
	 * If you're interested in the details you can look at <a
	 * href="http://jira.springframework.org/browse/INT-645"
	 * >http://jira.springframework.org/browse/INT-645</a>
	 */
	public static abstract class RewardNetworkMock implements RewardNetwork {

		public static RewardNetwork getMock() {
			return mock(RewardNetworkMock.class);
		}
	}

}
