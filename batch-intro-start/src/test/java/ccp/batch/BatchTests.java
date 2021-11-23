package ccp.batch;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/system-test-config.xml")
public class BatchTests {
	
	
	@Autowired JobLauncher jobLauncher;
	@Autowired JobRegistry jobRegistry;
	@Autowired QueueViewMBean diningQueueView;
	JdbcTemplate jdbcTemplate;

	@Autowired
	public void initJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	@Test
	public void runBatch() throws Exception {
		Job job = jobRegistry.getJob("resendUnprocessedDiningsJob");
		Map<String, JobParameter> map = new HashMap<String, JobParameter>();
		map.put("startDate", new JobParameter(new Date()));
		JobParameters params = new JobParameters(map);
		JobExecution execution = jobLauncher.run(job, params);

		// TODO 11: Find the "resendUnprocessedDiningsJob" Job using the jobRegistry and
		// use the JobLauncher to launch the job with the given JobParameters.
		// Assert that the resulting JobExecution has an exitStatus of ExitStatus.COMPLETED
		// and use the jdbcTemplate to assert that the number of confirmed dinings in the database
		// is now 150. (the same as the number of confirmation messages that were on the queue.)

		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		int processed = jdbcTemplate.queryForInt("select count(*) from T_DINING where CONFIRMED=1");
		assertEquals(150, processed);

		// TODO 17: assert that the batch sent 150 messages using the diningQueueView's queueSize property

		assertEquals(150L, diningQueueView.getQueueSize());
	}
}
