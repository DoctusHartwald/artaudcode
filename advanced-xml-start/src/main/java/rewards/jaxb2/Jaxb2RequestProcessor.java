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
			
			// TODO 04: use the context to create an Unmarshaller and unmarshal
			// the given source. Convert the resulting DiningRequests to a
			// List<Dining> using the extractDinings helper method.
			Unmarshaller unmarshaller = context.createUnmarshaller();
			DiningRequests requests =  (DiningRequests)unmarshaller.unmarshal(source);
			return extractDinings(requests);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void marshalDiningRequests(List<Dining> dinings, Result result) {
		try {
			JAXBContext context = JAXBContext.newInstance("rewards.request");
			// TODO 05: use the context to create a Marshaller and marshal
			// the dinings to the given result. Note that you cannot marshal
			// the list directly, so use the createDiningRequests helper method.
			// Set the "jaxb.formatted.output" property on the Marshaller
			// to Boolean.TRUE first!
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			DiningRequests requests = createDiningRequests(dinings);
			marshaller.marshal(requests, result);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

	}
}
