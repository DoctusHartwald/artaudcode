package com.batch.factory;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

@ContextConfiguration(locations = "classpath:com/batch/factory/contextFactory.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GenFactoryTestCase extends AbstractDependencyInjectionSpringContextTests {

	private final static Logger logger = Logger.getLogger(GenFactoryTestCase.class);

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
		logger.info(">>> TIME ELAPSED:" + sw.prettyPrint());
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
