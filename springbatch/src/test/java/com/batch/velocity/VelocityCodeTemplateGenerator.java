package com.batch.velocity;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.batch.model.Ledger;


/**
 * 
 * @author artaud
 *
 */
@ContextConfiguration(locations = "classpath:com/batch/velocity/contextVelocity.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class VelocityCodeTemplateGenerator {

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private FlatFileItemReader vFlatFileItemReader;

	@Test
	public void testSimpleTemplate(){
		
		
		System.out.println("[INFO] -------------------------------------------------------------------------------------------");
		System.out.println("[INFO] Running test : testSimpleTemplate / "+ getClass().getName());
		System.out.println("[INFO] -------------------------------------------------------------------------------------------");

		
		Map model = new HashMap();
		model.put("name", "Velocity");
		model.put("project", "Jakarta");


		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/batch/velocity/example2.vm", model);
		System.out.println(" "+text);
		Assert.assertNotNull(text);
	}

	@Test
	public void testFlatReaderItemToVelocityProcessor(){
		
		
		
		System.out.println("[INFO] ----------------------------------------------------------------------------------");
		System.out.println("[INFO] Running test : testFlatReaderItemToVelocityProcessor / "+ getClass().getName());
		System.out.println("[INFO] ----------------------------------------------------------------------------------");

		
		
		vFlatFileItemReader.open(new ExecutionContext());
		boolean hasNext = true ; 

		Project  vProject = null;

		while (hasNext) {
			try {
				vProject = (Project) vFlatFileItemReader.read();

			} catch (UnexpectedInputException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}


			if (vProject == null) {
				hasNext = false;
			}
			else {
				

				Map model = new HashMap();
				model.put("name", vProject.getName());
				model.put("project", vProject.getProject());


				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/batch/velocity/example2.vm", model);
				System.out.println(" "+text);

				System.out.println(vProject.toString());
			}
		}
	}

}
