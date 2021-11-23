package rewards.messaging;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessagingException;
import org.springframework.integration.transformer.MessageTransformationException;
import org.springframework.integration.xml.source.DomSourceFactory;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;

import rewards.Dining;

public class DiningRequestUnmarshaller implements Unmarshaller {

	private XPathOperations xpathTemplate = new Jaxp13XPathTemplate();

	private final TransformerFactory transformerFactory = TransformerFactory.newInstance();

	private DomSourceFactory domSourceFactory = new DomSourceFactory();

	@Override
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.isAssignableFrom(Dining.class);
	}

	@Override
	@Transformer
	public Object unmarshal(Source source) throws XmlMappingException,
			IOException {
		if (source instanceof StringSource) {
			StringResult result = new StringResult();
			javax.xml.transform.Transformer transformer = getTransformer();
			try {
				transformer.transform((StringSource) source, result);
			} catch (TransformerException e) {
				throw new MessageTransformationException("failed to unmarshal source", e);
			}
			source = this.domSourceFactory.createSource(result.toString());
		}

		String transactionId = xpathTemplate.evaluateAsString(
				"/dining/@transaction-id", source);
		String amount = xpathTemplate.evaluateAsString("/dining/amount/@value",
				source);
		String creditCardNumber = xpathTemplate.evaluateAsString(
				"/dining/creditcard/@number", source);
		String merchantNumber = xpathTemplate.evaluateAsString(
				"/dining/merchant/@number", source);
		String dateString = xpathTemplate.evaluateAsString(
				"/dining/timestamp/date", source);
		int year = Integer.valueOf(dateString.substring(0,4));
		int month = Integer.valueOf(dateString.substring(5,7));
		int day = Integer.valueOf(dateString.substring(8,10));
		return Dining.createDining(transactionId, amount,
				creditCardNumber,merchantNumber,month, day,year);
	}

	protected synchronized javax.xml.transform.Transformer getTransformer() {
		try {
			return transformerFactory.newTransformer();
		} catch (Exception e) {
			throw new MessagingException("Exception creating transformer", e);
		}
	}

}
