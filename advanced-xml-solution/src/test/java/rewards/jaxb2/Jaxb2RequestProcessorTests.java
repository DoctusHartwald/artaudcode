package rewards.jaxb2;

import rewards.AbstractRequestProcessor;
import rewards.AbstractRequestProcessorTest;

public class Jaxb2RequestProcessorTests extends AbstractRequestProcessorTest {

	@Override
	protected AbstractRequestProcessor createProcessor() {
		return new Jaxb2RequestProcessor();
	}	
}
