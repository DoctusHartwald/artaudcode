package com.batch.velocity.genClass;

import org.apache.velocity.util.StringUtils;

public class GeneratorUtility {


	
	
	/**
	 * set$utility.firstToUpperCase($att.Name)
	 * @param input the input to set
	 */
	public String  getInputU(String pInput) {
		pInput.split("_");
		return StringUtils.capitalizeFirstLetter(pInput);
	}
}
