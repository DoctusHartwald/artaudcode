package com.batch.velocity.genClass;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.batch.io.OutputFileWriter;
import com.batch.io.SingleItemWriter;


/**
 * 
 * @author artaud
 *
 */
@ContextConfiguration(locations = "classpath:com/batch/velocity/genClass/contextVelocity.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class VelocityCodeTemplateGenClassGenerator {

	private static final Logger LOG = Logger.getLogger(VelocityCodeTemplateGenClassGenerator.class);


	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private FlatFileItemReader vFlatFileItemReader;

	@Autowired
	@Qualifier("outputWriter")
	private OutputFileWriter  vSingleItemWriter;

	@Test
	public void testFlatReaderItemToVelocityProcessor(){



		System.out.println("[INFO] ----------------------------------------------------------------------------------");
		System.out.println("[INFO] Running test : testFlatReaderItemToVelocityProcessor / "+ getClass().getName());
		System.out.println("[INFO] ----------------------------------------------------------------------------------");



		vFlatFileItemReader.open(new ExecutionContext());
		boolean hasNext = true ; 

		GenMapperFactory  vGenMapperFactory = null;

		GeneratorUtility vUtility = new GeneratorUtility();
		List<GenMapperFactory> vGenMapperFactoryList = new ArrayList<GenMapperFactory>();
		String text = null;


		while (hasNext) {
			try {
				vGenMapperFactory = (GenMapperFactory) vFlatFileItemReader.read();
				vGenMapperFactoryList.add(vGenMapperFactory);

			} catch (UnexpectedInputException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}


			if (vGenMapperFactory == null) {
				hasNext = false;
			}
			else{
				Map model = new HashMap();
				model.put("utility", vUtility);
				model.put("mylist", vGenMapperFactoryList);
				model.put("name", "ClassDeTest");

				text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/batch/velocity/genClass/GenClasse-Template.vm", model);
				LOG.info(vGenMapperFactory.toString());

			}

		}

		BufferedWriter writer;
		try {

			
			LOG.info(" "+text);
			
			AbtractFactoryMapper vAbtractFactoryMapper = new GenMapperFactory() ;
			vAbtractFactoryMapper.setOutPutStream(text);

			//vSingleItemWriter.open(new ExecutionContext());
			List<GenMapperFactory> vGenMapperFactoryList1 = new ArrayList<GenMapperFactory>();  
			vGenMapperFactoryList1.add((GenMapperFactory) vAbtractFactoryMapper);
			
			vSingleItemWriter.write(vGenMapperFactoryList1);

		} catch (IOException e) {

			e.printStackTrace();
		}
		catch (Exception e) {

			e.printStackTrace();
		}

		LOG.info("Class " + "ClassDeTest" + " generated!");

	}

}
