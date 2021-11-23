package rewards.messaging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

import rewards.Dining;

public class DiningRequestUnmarshallerTests {

	private Source diningSource;

	private DiningRequestUnmarshaller unmarshaller = new DiningRequestUnmarshaller();

	@Before
	public void setup() throws Exception {
		diningSource = new DOMSource(DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(
						new ClassPathResource("dining-sample.xml", this
								.getClass()).getFile()));
	}

	@Test
	public void unmarshall() throws Exception {
		Dining dining = (Dining) unmarshaller.unmarshal(diningSource);
		assertThat(dining.getAmount(), is(MonetaryAmount.valueOf("10.5")));
		assertThat(dining.getCreditCardNumber(), is("1234123412340003"));
		assertThat(dining.getMerchantNumber(), is("1234567890"));
		assertThat(dining.getDate(), is(new SimpleDate(4,21,2009)));
	}

}
