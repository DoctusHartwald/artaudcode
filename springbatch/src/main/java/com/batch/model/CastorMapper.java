package com.batch.model;

/*
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.castor.xml.BackwardCompatibilityContext;
import org.exolab.castor.tools.MappingTool;
import org.exolab.castor.xml.XMLContext;

/** 
 * generate a Castor mapping file ready for editing 
 */ 

public class CastorMapper { 

	private MappingTool tool = null;
	private static Log log = LogFactory.getFactory().getInstance (CastorMapper.class);

	private static final String OUTPUT_FILE = "./output/ledger-mapping.xml";

	public CastorMapper() { 
		try { 
			tool = new XMLContext().createMappingTool();
		} 
		catch (Exception e) {  
			log.error (e.getClass().getName(), e);  
		}  
	}  

	public void createMapping (Class[] someClasses, String outputFile) {
		try {
			for (int i = 0; i < someClasses.length; i++) {
				tool.addClass(someClasses[i]); 
			}
			File file = new File (outputFile);
			log.debug ("Output will has been created at " + file.getAbsolutePath());
			OutputStream stream = new FileOutputStream(file);  

			Writer writer = new OutputStreamWriter(stream);  
			tool.write(writer);  
			writer.close();
		} 
		catch (Exception e) {  
			log.error (e.getClass().getName(), e);  
		}  
	}

	public void createMapping (Class aClass, String outputFile) {
		try {
			tool.addClass(aClass); 
			tool.setForceIntrospection(true);
			//tool.setInternalContext(new BackwardCompatibilityContext());
			File file = new File (outputFile);
			log.info ("Output will has been created at " + file.getAbsolutePath());
			OutputStream stream = new FileOutputStream(file);  

			Writer writer = new OutputStreamWriter(stream);  
			tool.write(writer);  
			writer.close();
		} 
		catch (Exception e) {  
			log.error (e.getClass().getName(), e);  
		}  
	}

	public static void main(String[] args) {  
		CastorMapper mapper = new CastorMapper();
		mapper.createMapping (Ledger.class, OUTPUT_FILE);
		mapper.createMapping (Check.class, OUTPUT_FILE);
		

		System.out.println("\nFin");
	}  

} 

