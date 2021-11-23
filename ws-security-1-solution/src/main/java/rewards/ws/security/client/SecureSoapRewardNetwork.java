package rewards.ws.security.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.dom.DOMResult;

import org.springframework.util.xml.DomUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.AccountContribution.Distribution;

import common.money.MonetaryAmount;
import common.money.Percentage;

public class SecureSoapRewardNetwork implements RewardNetwork {
	private static final String REQUEST_TEMPLATE = 
	    "<rewardAccountForDiningRequest xmlns=\"http://www.springsource.com/reward-network\">\n"
	  + "  <dining amount=\"%s\" creditCardNumber=\"%s\" merchantNumber=\"%s\"/>\n"
	  + "</rewardAccountForDiningRequest>";
	
	private WebServiceTemplate wsTemplate;

	public void setWebServiceTemplate(WebServiceTemplate wsTemplate) {
		this.wsTemplate = wsTemplate;
	}
	
	public RewardConfirmation rewardAccountFor(Dining dining) {
		String requestBody = String.format(REQUEST_TEMPLATE, 
				dining.getAmount(), dining.getCreditCardNumber(), dining.getMerchantNumber());
		DOMResult result = new DOMResult();
		wsTemplate.sendSourceAndReceiveToResult(new StringSource(requestBody), result);
		return processResponse(((Document) result.getNode()).getDocumentElement());
	}

	private RewardConfirmation processResponse(Element responseElement) {
		return mapRewardConfirmation(DomUtils.getChildElementByTagName(responseElement, "rewardConfirmation"));
	}

	private RewardConfirmation mapRewardConfirmation(Element confirmationElement) {
		String confirmationNumber = confirmationElement.getAttribute("confirmationNumber");
		String accountNumber = confirmationElement.getAttribute("accountNumber");
		String amount = confirmationElement.getAttribute("amount");
		List<Element> distributionElements = DomUtils.getChildElementsByTagName(confirmationElement, "distribution");
		AccountContribution contribution = new AccountContribution(accountNumber, MonetaryAmount.valueOf(amount),
				mapDistributions(distributionElements));
		return new RewardConfirmation(confirmationNumber, contribution);
	}

	private Set<Distribution> mapDistributions(List<Element> distributionElements) {
		Set<Distribution> distributions = new HashSet<Distribution>(distributionElements.size());
		for (Element element : distributionElements) {
			String beneficiary = element.getAttribute("beneficiary");
			String amount = element.getAttribute("amount");
			String percentage = element.getAttribute("percentage");
			String totalSavings = element.getAttribute("totalSavings");
			Distribution distribution = new Distribution(beneficiary, MonetaryAmount.valueOf(amount), Percentage
					.valueOf(percentage), MonetaryAmount.valueOf(totalSavings));
			distributions.add(distribution);
		}
		return distributions;
	}

}
