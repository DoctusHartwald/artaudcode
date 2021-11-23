package org.springbyexample.batch;

/**
 *
 */


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class TodoListsCsvReaderTest {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher launcher;

	@Autowired
	private AddToCollectionTodoListWriter writer;

	@Test public void todoListsCsvReader() throws Exception {
		JobExecution jobExecution = launcher.run(job, new JobParameters());
		Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		Assert.assertEquals(3,writer.getTodoLists().size());
	}

}
