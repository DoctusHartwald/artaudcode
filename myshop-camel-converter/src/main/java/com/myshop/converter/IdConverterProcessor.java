package com.myshop.converter;

import org.apache.camel.Converter;

@Converter
public class IdConverterProcessor {

	@Converter
    public static String toIntString(int input) throws Exception {
		return new Integer(input).toString();
    }

}
