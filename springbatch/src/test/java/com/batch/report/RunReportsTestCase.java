package com.batch.report;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = "classpath:com/batch/todb/contextToDB.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RunReportsTestCase {
	@Autowired
	private IReportRunner runner;

	@Before
	public void setup() {
	}

	@Test
	public void testRunReports() throws Exception {
		runner.runReports();
	}
}
