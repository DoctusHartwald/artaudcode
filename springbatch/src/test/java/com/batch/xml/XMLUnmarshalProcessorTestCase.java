package com.batch.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.batch.model.Ledger;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


@ContextConfiguration(locations = "classpath:com/batch/xml/contextUnmarshalXML.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class XMLUnmarshalProcessorTestCase {

	private Ledger  ledger = null;
	@Autowired
	private StaxEventItemReader unmarshallerBean;

	@Test
	public void testReadXmlFile() {
		////System.out.println(unmarshallerBean.getXStream().);

		// Instanciation de la classe XStream
		XStream xstream = new XStream(new DomDriver());

		// Redirection du fichier c:/temp/article.xml vers un flux
		// d'entrée fichier
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File("/home/artaud/workspace/springbatch/output/members-ledger.xml"));
			ledger = (Ledger)xstream.fromXML(fis);
			System.out.println(ledger.toString());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}
}
