package rewards.batch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:batch-processor-config.xml")
public class DiningBatchProcessorTests {

	private static final String TABLE = "T_REWARD";

	private DiningBatchProcessor diningBatchProcessor;
	private SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	void init(DiningBatchProcessor diningBatchProcessor, DataSource dataSource) {
		this.diningBatchProcessor = diningBatchProcessor;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	public void testProcessBatch() throws IOException {
		assertThat(countRowsInTable(jdbcTemplate, TABLE), equalTo(0));

		Resource diningBatchCsvFile = new ClassPathResource("dining-input-small.csv");
		diningBatchProcessor.processBatch(diningBatchCsvFile);

		// ensure that all batch records actually made it into the database
		assertThat(countRowsInTable(jdbcTemplate, TABLE), equalTo(5));

		// clear out the db for the next test
		deleteFromTables(jdbcTemplate, TABLE);
	}

}
