package com.batch.todb;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.StopWatch;

@ContextConfiguration(locations = "classpath:com/batch/todb/contextToDB.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ToDBBatchTestCase extends
		AbstractTransactionalJUnit4SpringContextTests {

	private final static Logger logger = Logger.getLogger(ToDBBatchTestCase.class);

	@Autowired
	private JobLauncher launcher;

	@Autowired
	private Job job;
	private JobParameters jobParameters = new JobParameters();

	@Before
	public void setup() {
	}

	@Test
	public void testLaunchJob() throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		launcher.run(job, jobParameters);
		sw.stop();
		logger.info(">>> TIME ELAPSED:" + sw.shortSummary());

	}

	@Autowired
	public void setLauncher(JobLauncher bootstrap) {
		this.launcher = bootstrap;
	}

	@Autowired
	public void setJob(Job job) {
		this.job = job;
	}
}
