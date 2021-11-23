package rewards.jaxb2;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

import rewards.AbstractRequestProcessor;
import rewards.Dining;
import rewards.request.DiningRequests;

public class Jaxb2RequestProcessor extends AbstractRequestProcessor {

	@Override
	public List<Dining> unmarshalDiningRequests(Source source) {
		try {
			JAXBContext context = JAXBContext.newInstance("rewards.request");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			DiningRequests requests = (DiningRequests) unmarshaller.unmarshal(source);
			return extractDinings(requests);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void marshalDiningRequests(List<Dining> dinings, Result result) {
		try {
			JAXBContext context = JAXBContext.newInstance("rewards.request");
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			DiningRequests requests = createDiningRequests(dinings);
			marshaller.marshal(requests, result);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

	}
}
