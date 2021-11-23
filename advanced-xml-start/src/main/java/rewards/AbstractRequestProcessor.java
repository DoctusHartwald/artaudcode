package rewards;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import rewards.request.DiningRequest;
import rewards.request.DiningRequestConverter;
import rewards.request.DiningRequests;
import rewards.request.ObjectFactory;

public abstract class AbstractRequestProcessor {

	private DiningRequestConverter converter = new DiningRequestConverter();

	public abstract List<Dining> unmarshalDiningRequests(Source source);

	public abstract void marshalDiningRequests(List<Dining> dinings, Result result);

	/**
	 * Converts DiningRequests into List&lt;Dining&gt;
	 */
	protected List<Dining> extractDinings(DiningRequests requests) {
		List<Dining> dinings = new ArrayList<Dining>();
		for (DiningRequest request : requests.getDining()) {
			dinings.add(converter.createDining(request));
		}
		return dinings;
	}

	/**
	 * Converts List&lt;Dining&gt; into DiningRequests
	 */
	protected DiningRequests createDiningRequests(List<Dining> dinings) {
		DiningRequests requests = new ObjectFactory().createDiningRequests();
		List<DiningRequest> list = requests.getDining();
		for (Dining dining : dinings) {
			DiningRequest request = converter.createDiningRequest(dining);
			list.add(request);
		}
		return requests;
	}

}