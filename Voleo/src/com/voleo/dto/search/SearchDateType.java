package com.voleo.dto.search;

import com.voleo.entity.document.Document;

public enum SearchDateType {
	CREATE(Document.CREATE_DATE_FIELD),
	UPDATE(Document.UPDATE_DATE_FIELD);
	
	private final String fieldName;
	
	private SearchDateType(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
}
