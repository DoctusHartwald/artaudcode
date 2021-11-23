package rewards.request;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import rewards.Dining;
import rewards.request.DiningRequest.Amount;
import rewards.request.DiningRequest.Creditcard;
import rewards.request.DiningRequest.Merchant;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

public class DiningRequestConverter {

	public DiningRequest createDiningRequest(Dining dining) {
		ObjectFactory factory = new ObjectFactory();
		DiningRequest diningRequest = factory.createDiningRequest();

		Amount amount = factory.createDiningRequestAmount();
		amount.setValue(dining.getAmount().asBigDecimal());
		diningRequest.setAmount(amount);

		Creditcard creditcard = factory.createDiningRequestCreditcard();
		creditcard.setNumber(dining.getCreditCardNumber());
		diningRequest.setCreditcard(creditcard);

		Merchant merchant = factory.createDiningRequestMerchant();
		merchant.setNumber(dining.getMerchantNumber());
		diningRequest.setMerchant(merchant);

		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(dining.getDate().asDate());
		XMLGregorianCalendar cal;
		try {
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
		cal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		Timestamp timestamp = factory.createTimestamp();
		timestamp.setDate(cal);
		timestamp.setTime(cal);
		diningRequest.setTimestamp(timestamp);

		return diningRequest;
	}

	public Dining createDining(DiningRequest request) {
		MonetaryAmount money = new MonetaryAmount(request.getAmount().getValue());
		Date date = request.getTimestamp().getDate().toGregorianCalendar().getTime();
		// note that we have to ignore request.timestamp.time as SimpleDate doesn't support it
		SimpleDate simpleDate = SimpleDate.valueOf(date);
		return new Dining(money, request.getCreditcard().getNumber(), request.getMerchant().getNumber(), simpleDate);
	}

}
