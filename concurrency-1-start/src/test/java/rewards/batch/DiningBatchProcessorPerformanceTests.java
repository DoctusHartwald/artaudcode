package rewards.batch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.jdbc.SimpleJdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.SimpleJdbcTestUtils.deleteFromTables;

import java.io.IOException;

import javax.sql.DataSource;

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

	private static final String TABLE = "T_REWARD";
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
		Resource csvDiningBatch = new ClassPathResource("dining-input-small.csv");

		// TODO 04: call doTimedBatchProcessing
	}

	@Test
	public void testProcessLargeBatchIsFastEnough() throws IOException {
		Resource csvDiningBatch = new ClassPathResource("dining-input-large.csv");

		// TODO 08: call doTimedBatchProcessing
	}

	private void doTimedBatchProcessing(Resource csvDiningBatch, int expectedBatchSize) throws IOException {
		assertThat(countRowsInTable(jdbcTemplate, TABLE), equalTo(0));

		StopWatch stopWatch = new StopWatch();

		// TODO 05: start the StopWatch

		diningBatchProcessor.processBatch(csvDiningBatch);

		// TODO 06: stop the StopWatch

		// TODO 07: set totalMs equal to the total number of milliseconds
		long totalMs = Long.MAX_VALUE;

		// was the batch processed within the required amount of time?
		assertTrue("took too long! max ms: " + MAX_SLA_MILLIS + "; actual ms: " + totalMs,
				totalMs < MAX_SLA_MILLIS);

		// ensure that all batch records actually made it into the database
		assertThat(countRowsInTable(jdbcTemplate, TABLE), equalTo(expectedBatchSize));

		// clear out the db for the next test
		deleteFromTables(jdbcTemplate, TABLE);
	}

}
