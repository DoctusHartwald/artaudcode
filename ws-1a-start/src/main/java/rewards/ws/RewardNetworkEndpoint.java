package rewards.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.ws.types.RewardAccountForDiningRequest;
import rewards.ws.types.RewardAccountForDiningResponse;

@Endpoint
public class RewardNetworkEndpoint {

	private static final String NAMESPACE_URI = "http://www.springsource.com/reward-network";

	private RewardNetwork rewardNetwork;

	public RewardNetworkEndpoint(RewardNetwork rewardNetwork) {
		this.rewardNetwork = rewardNetwork;
	}
	
	//TODO 3: Create a new method with the name invoke
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="rewardAccountForDiningRequest")
	public RewardAccountForDiningResponse invoke(RewardAccountForDiningRequest req) {
		Dining dining = Dining.createDining(req.getAmount().toString(), req.getCreditCardNumber(), req.getMerchantNumber());
		
		
		RewardConfirmation confirmation = rewardNetwork.rewardAccountFor(dining);
		
		RewardAccountForDiningResponse response = new RewardAccountForDiningResponse();
		
		response.setAccountNumber(confirmation.getAccountContribution().getAccountNumber());
		response.setAmount(confirmation.getAccountContribution().getAmount().asBigDecimal());
		response.setConfirmationNumber(confirmation.getConfirmationNumber());
		
		return response;
	}

}