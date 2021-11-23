package rewards;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

public abstract class AbstractRequestProcessorTest {

	protected abstract AbstractRequestProcessor createProcessor();

	@Test
	public void parseRequests() {
		Source source = new StreamSource(getClass().getResourceAsStream("/dining-requests.xml"));
		List<Dining> dinings = createProcessor().unmarshalDiningRequests(source);

		assertEquals(2, dinings.size());
		Dining dining = dinings.get(0);
		assertEquals("1234123412341234", dining.getCreditCardNumber());
		assertEquals("1234567890", dining.getMerchantNumber());
		assertEquals(MonetaryAmount.valueOf("100.00"), dining.getAmount());
		assertEquals(new SimpleDate(4, 15, 2009), dining.getDate());
	}

	@Test
	public void writeDinings() throws ParseException, IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Dining d1 = new Dining(MonetaryAmount.valueOf("100"), 
							   "1234123412341234", 
							   "1234567890",
							   SimpleDate.valueOf(df.parse("2009-04-25")));
		Dining d2 = new Dining(MonetaryAmount.valueOf("150"), 
				   			   "1234123412341235", 
				   			   "1234567891",
				   			   SimpleDate.valueOf(df.parse("2009-04-26")));
		List<Dining> dinings = new ArrayList<Dining>();
		dinings.add(d1);
		dinings.add(d2);

		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		createProcessor().marshalDiningRequests(dinings, result);

		Reader reader = new InputStreamReader(getClass().getResourceAsStream("/marshalled-dinings.xml"));
		String expected = FileCopyUtils.copyToString(reader);
		reader.close();
		assertEquals(expected, writer.toString());
		System.out.println(writer);
	}

}
