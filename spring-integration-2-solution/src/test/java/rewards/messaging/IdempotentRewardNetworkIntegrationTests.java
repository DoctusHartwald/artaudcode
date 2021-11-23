package rewards.messaging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("confirmations")
	PollableChannel confirmations;


	@Test
	public void configOk() throws Exception {
		//
	}

	@Test
	public void idempotence() throws Exception {
		RewardConfirmation confirmation = mock(RewardConfirmation.class);
		when(delegate.rewardAccountFor(dining)).thenReturn(confirmation);
		inputGateway.process(dining);
		RewardConfirmation firstConfirmation = (RewardConfirmation) confirmations.receive(1000).getPayload();
		when(rewardRepository.findConfirmationFor(dining)).thenReturn(
				firstConfirmation);
		inputGateway.process(dining);
		RewardConfirmation secondConfirmation = (RewardConfirmation) confirmations.receive(1000).getPayload();
		assertThat(secondConfirmation, is(firstConfirmation));
		verify(delegate, times(1)).rewardAccountFor(dining);
	}

	public static abstract class RewardNetworkMock implements RewardNetwork {

		public static RewardNetwork getMock(){
			//mocking the interface directly doesn't work per INT-654
			return mock(RewardNetworkMock.class);
		}
	}
	public static abstract class RewardRepositoryMock implements RewardRepository {

		public static RewardRepository getMock(){
			//mocking the interface directly doesn't work per INT-654
			return mock(RewardRepository.class);
		}
	}
}
