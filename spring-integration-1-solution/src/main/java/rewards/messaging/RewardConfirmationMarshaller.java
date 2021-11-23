package rewards.messaging;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.converters.DOMConverter;
import nu.xom.converters.SAXConverter;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.MarshallingFailureException;
import org.springframework.oxm.XmlMappingException;
import org.springframework.util.Assert;
import org.xml.sax.SAXException;

import rewards.RewardConfirmation;

public class RewardConfirmationMarshaller implements Marshaller {

	@Override
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.isAssignableFrom(RewardConfirmation.class);
	}

	@Override
	public void marshal(Object graph, Result result)
			throws XmlMappingException, IOException {
		RewardConfirmation rewardConfirmation = (RewardConfirmation) graph;
		Element root = new Element("reward-confirmation");
		Assert.hasText(rewardConfirmation.getDiningTransactionId(),
				"'diningTransactionId' is missing from reward confirmation");
		root.addAttribute(new Attribute("dining-transaction-id",
				rewardConfirmation.getDiningTransactionId()));
		Document newDocument = new Document(root);
		if (result instanceof StreamResult) {
			StreamResult streamResult = (StreamResult) result;
			streamResult.getWriter().append(newDocument.toXML());
			return;
		}
		if (result instanceof DOMResult) {
			DOMResult domResult = (DOMResult) result;
			try {
				domResult.setNode(DOMConverter.convert(newDocument,
						DocumentBuilderFactory.newInstance()
								.newDocumentBuilder().getDOMImplementation()));
			} catch (ParserConfigurationException e) {
				throw new MarshallingFailureException(
						"DOM configuration failed ", e);
			}
			return;
		}
		if (result instanceof SAXResult) {
			SAXResult saxResult = (SAXResult) result;
			try {
				new SAXConverter(saxResult.getHandler()).convert(newDocument);
			} catch (SAXException e) {
				throw new MarshallingFailureException(
						"SAX configuration failed", e);
			}
			return;
		}

		throw new IllegalArgumentException("Got instance of "
				+ result.getClass().getSimpleName()
				+ " which is not supported");

	}
}
