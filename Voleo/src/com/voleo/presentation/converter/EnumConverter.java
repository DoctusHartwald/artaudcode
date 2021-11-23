package com.voleo.presentation.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class EnumConverter extends StrutsTypeConverter {

	@SuppressWarnings("unchecked")
	@Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
		return Enum.valueOf(toClass, values[0]);
	}

	
	@Override
	public String convertToString(Map context, Object o) {
		return o.toString();
	}

}
