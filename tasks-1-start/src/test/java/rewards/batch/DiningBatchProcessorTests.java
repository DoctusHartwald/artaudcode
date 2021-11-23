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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:batch-processor-config.xml")
public class DiningBatchProcessorTests {

	private static final String COUNT_REWARDS_SQL = "select count(*) from T_REWARD";
	private static final String DELETE_ALL_REWARDS = "delete from T_REWARD";

	private DiningBatchProcessor diningBatchProcessor;
	private SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	void init(DiningBatchProcessor diningBatchProcessor, DataSource dataSource) {
		this.diningBatchProcessor = diningBatchProcessor;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	public void testProcessBatch() throws IOException {
		assertThat(jdbcTemplate.queryForInt(COUNT_REWARDS_SQL), equalTo(0));

		Resource diningBatchCsvFile = new ClassPathResource("dining-input-small.csv");
		diningBatchProcessor.processBatch(diningBatchCsvFile);

		// ensure that all batch records actually made it into the database
		assertThat(jdbcTemplate.queryForInt(COUNT_REWARDS_SQL), equalTo(5));
	}

	@After
	public void tearDown() {
		// clear out the db for the next test
		jdbcTemplate.update(DELETE_ALL_REWARDS);
	}

}
