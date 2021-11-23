
import org.apache.camel.CamelContext;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import com.myshop.input.InputReportIncident;

import com.myshop.integration.route.SupplierRouteDefinition;
/**
 * ContextTestSupport is a supporting unit test class for much easier unit testing with Apache Camel.
 * @author artaud
 *
 */
public class ReportRouteIncidentRouteTest  extends ContextTestSupport  {
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new SupplierRouteDefinition();
	}
	public void reportIncident(){
		// create the producer template to use for sending messages
		SupplierRouteDefinition supplier = new SupplierRouteDefinition();
		InputReportIncident parameters = createInput();

		// create the producer template to use for sending messages  
		ProducerTemplate producer = context.createProducerTemplate(); 
		//Object out = context.createProducerTemplate().sendBody("direct:start",parameters);

		// Send the message to an in-memory queue and return  
		// See GenerateEmailStoreToFilePollFolderAndSendFile  
		// it will read the message from direct:start in-memory queue 
		Object mailBody = producer.sendBody("direct:start", parameters);  
		System.out.println("Body:" + mailBody);  
	}
	/**
	 * Creates a dummy request to be used for input
	 */
	protected InputReportIncident createInput() {
		InputReportIncident input = new InputReportIncident();
		input.setId(1);
		input.setFirstName("Antoine");
		input.setName("ARTAUD");
		return input ;
	}
}
