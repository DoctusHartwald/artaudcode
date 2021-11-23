package rewards.oxm;

import java.util.Collections;
import java.util.Map;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import rewards.AbstractRequestProcessor;
import rewards.AbstractRequestProcessorTest;

public class OxmRequestProcessorTests extends AbstractRequestProcessorTest {

	@Override
	protected AbstractRequestProcessor createProcessor() {
		Jaxb2Marshaller marshaller = createMarshaller();
		// Jaxb2Marshaller can be used for marshalling and unmarshalling
		return new OxmRequestProcessor(marshaller, marshaller);
	}

	// would typically be defined as a Spring bean instead
	private Jaxb2Marshaller createMarshaller() {
		// TODO 06: create a Jaxb2Marshaller and set its setContextPath and
		// marshallerProperties properties. Call afterPropertiesSet() before
		// returning the Jaxb2Marshaller, as we are not using Spring to call
		// it for us! Simply rethrow all Exceptions as RuntimeExceptions.
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("rewards.request");
		Map props = Collections.singletonMap("jaxb.formatted.output", Boolean.TRUE);
		marshaller.setMarshallerProperties(props);
		try {
			marshaller.afterPropertiesSet();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return marshaller;
	//	throw new UnsupportedOperationException("Please implement TODO 06!");
	}
}
