package com.batch.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReportRunnerImpl extends JdbcTemplate implements IReportRunner {

	public void runReports() throws Exception {
		InputStream input = new FileInputStream(new File("C:/workspace-PRT/springbatch/src/test/resources/com/batch/report/ReportByMemberName.xml"));

		// "C:/mathew/springbatch/src/com/batch/todb/ReportByMemberName.xml"));

		JasperDesign design = JRXmlLoader.load(input);
		JasperReport report = JasperCompileManager.compileReport(design);

		Map params = new HashMap();
		params.put("ReportTitle", "Report By Member Name");

		JasperPrint print = JasperFillManager.fillReport(report, params, super
				.getDataSource().getConnection());

		OutputStream output = new FileOutputStream(new File(
				"C:/temp/reportbymembername.pdf"));
		JasperExportManager.exportReportToPdfStream(print, output);

	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

}
