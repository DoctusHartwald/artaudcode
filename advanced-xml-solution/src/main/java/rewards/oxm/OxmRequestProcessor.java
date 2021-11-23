package rewards.oxm;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import rewards.AbstractRequestProcessor;
import rewards.Dining;
import rewards.request.DiningRequests;

public class OxmRequestProcessor extends AbstractRequestProcessor {

	private Unmarshaller unmarshaller;
	private Marshaller marshaller;

	// Note that dependency is just on Spring-OXM's (Un)marshaller: 
	// this can be any implementation!
	public OxmRequestProcessor(Unmarshaller unmarshaller, Marshaller marshaller) {
		this.unmarshaller = unmarshaller;
		this.marshaller = marshaller;
	}

	@Override
	public List<Dining> unmarshalDiningRequests(Source source) {
		try {
			DiningRequests requests = (DiningRequests) unmarshaller.unmarshal(source);
			return extractDinings(requests);
		} catch (IOException e) {
			throw new RuntimeException("Can't read xml file", e);
		}
	}

	@Override
	public void marshalDiningRequests(List<Dining> dinings, Result result) {
		DiningRequests requests = createDiningRequests(dinings);
		try {
			marshaller.marshal(requests, result);
		} catch (IOException e) {
			throw new RuntimeException("error writing to Result", e);
		}
	}

}
