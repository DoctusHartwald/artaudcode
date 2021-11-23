package ccp.batch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import ccp.Confirmation;

public class ConfirmationUpdater implements ItemWriter<Confirmation> {
	private Log logger = LogFactory.getLog(getClass());
	private JdbcTemplate jdbcTemplate;

	public ConfirmationUpdater(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void write(final List<? extends Confirmation> items) throws Exception {
		logger.debug("Confirming " + items.size() + " dinings");
		// TODO 07: use jdbcTemplate.batchUpdate with an anonymous BatchPreparedStatementSetter
		// implementation to set the CONFIRMED column of the relevant T_DINING rows to 1

		jdbcTemplate.batchUpdate("update T_DINING set CONFIRMED=1 where ID=?", 
				new BatchPreparedStatementSetter() {
			public int getBatchSize() {	
				return items.size(); 
			}
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				String id = items.get(i).getTransactionId();
				ps.setString(1, id);
			}
		});
		//throw new UnsupportedOperationException("Please implement TODO 07!");
	}
}
