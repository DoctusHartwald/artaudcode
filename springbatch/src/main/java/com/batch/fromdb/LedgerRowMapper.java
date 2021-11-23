package com.batch.fromdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.batch.model.Check;
import com.batch.model.Ledger;

@Component("ledgerRowMapper")
public class LedgerRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ledger ledger = new Ledger();
		ledger.setId(rs.getInt("id"));
		ledger.setReceiptDate(rs.getDate("rcv_dt"));
		ledger.setMemberName(rs.getString("mbr_nm"));
		ledger.setCheckNumber(rs.getString("chk_nbr"));
		ledger.setCheckDate(rs.getDate("chk_dt"));
		ledger.setPaymentType(rs.getString("pymt_typ"));
		ledger.setDepositAmount(rs.getDouble("dpst_amt"));
		ledger.setPaymentAmount(rs.getDouble("pymt_amt"));
		ledger.setComments(rs.getString("comments"));
		
		List<Check> checks = new ArrayList<Check>();
		Check check = new Check();
		check.setCheckDate(rs.getDate("chk_dt"));
		check.setCheckNumber(rs.getString("chk_nbr"));
		checks.add(check);
		
		ledger.setChecks(checks);
		
		return ledger;
	}

}
