package rewards.batch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:batch-processor-config.xml")
public class DiningBatchProcessorPerformanceTests {

	private static final String COUNT_REWARDS_SQL = "select count(*) from T_REWARD";
	private static final String DELETE_ALL_REWARDS = "delete from T_REWARD";
	private static final long MAX_SLA_MILLIS = 5000;

	private DiningBatchProcessor diningBatchProcessor;
	private SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	void init(DiningBatchProcessor diningBatchProcessor, DataSource dataSource) {
		this.diningBatchProcessor = diningBatchProcessor;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	public void testProcessSmallBatchIsFastEnough() throws IOException {
		Resource diningBatchCsvFile = new ClassPathResource("dining-input-small.csv");

		doTimedBatchProcessing(diningBatchCsvFile, 5);
	}

	@Test
	public void testProcessLargeBatchIsFastEnough() throws IOException {
		Resource diningBatchCsvFile = new ClassPathResource("dining-input-large.csv");

		doTimedBatchProcessing(diningBatchCsvFile, 1000);
	}

	private void doTimedBatchProcessing(Resource csvDiningBatch, int expectedBatchSize) throws IOException {
		assertThat(jdbcTemplate.queryForInt(COUNT_REWARDS_SQL), equalTo(0));

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		diningBatchProcessor.processBatch(csvDiningBatch);

		stopWatch.stop();

		long totalMs = stopWatch.getTotalTimeMillis();

		// was the batch processed within the required amount of time?
		assertTrue("took too long! max ms: " + MAX_SLA_MILLIS + "; actual ms: " + totalMs,
				totalMs < MAX_SLA_MILLIS);

		// ensure that all batch records actually made it into the database
		assertThat(jdbcTemplate.queryForInt(COUNT_REWARDS_SQL), equalTo(expectedBatchSize));
	}
	
	@After
	public void tearDown() {
		// clear out the db for the next test
		jdbcTemplate.update(DELETE_ALL_REWARDS);
	}

}
