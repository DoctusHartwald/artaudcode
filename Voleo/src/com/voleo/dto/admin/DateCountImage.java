package com.voleo.dto.admin;

import java.util.Date;

public class DateCountImage {
	private Date date ;
	private String dateFormat ; 
	private int countNumberDocument;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public int getCountNumberDocument() {
		return countNumberDocument;
	}
	public void setCountNumberDocument(int countNumberDocument) {
		this.countNumberDocument = countNumberDocument;
	}
		
}
