package rewards.schema;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;

public class ValidatorTests  {
	@Test
	public void validate() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
			.newSchema(getClass().getResource("/dining-requests.xsd"));
		factory.setNamespaceAware(true);
		factory.setSchema(schema);
		DocumentBuilder builder = factory.newDocumentBuilder();
		// make sure errors are thrown as Exceptions to fail the test:
		builder.setErrorHandler(new DefaultErrorHandler());

		builder.parse(getClass().getResourceAsStream("/dining-requests.xml"));
	}
}
