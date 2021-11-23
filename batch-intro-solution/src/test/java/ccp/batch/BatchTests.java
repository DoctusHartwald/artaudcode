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

		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		int processed = jdbcTemplate.queryForInt("select count(*) from T_DINING where CONFIRMED=1");
		assertEquals(150, processed);

		assertEquals(150L, diningQueueView.getQueueSize());
	}
}
