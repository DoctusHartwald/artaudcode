package com.voleo.dto.admin;

import java.util.*;
public class DateCountCommentaire {

	private int countcommentaire ; 
	private String dateFormat ;
	private Date date;
	
	//Gettters and SETTERS 
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCountcommentaire() {
		return countcommentaire;
	}
	public void setCountcommentaire(int countcommentaire) {
		this.countcommentaire = countcommentaire;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	
}
