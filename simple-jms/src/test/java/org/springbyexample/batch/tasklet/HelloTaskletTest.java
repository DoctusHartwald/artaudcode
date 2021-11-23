package org.springbyexample.batch.tasklet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HelloTaskletTest {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Test public void helloTasklet() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
			"/org/springbyexample/batch/tasklet/HelloTaskletTest-context.xml"
		);

		Job job = (Job) context.getBean("helloJob");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		JobExecution exec = jobLauncher.run(job, new JobParameters());
		Assert.assertEquals(ExitStatus.COMPLETED.getExitCode(),exec.getExitStatus().getExitCode());
	}

}
