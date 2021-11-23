package com.voleo.dto.search;

import java.util.Date;

import com.voleo.entity.document.DocumentType;

public class SearchRequest {
	private String keywords;
	
	private Date firstDate;
	private Date secondDate;
	private SearchDateMode searchDateMode;
	private SearchDateType searchDateType;
	
	private DocumentType documentType;
	private Integer documentSize;
	
	private String userPseudo;
	
	public SearchRequest() {
		searchDateMode = SearchDateMode.AFTER;
		searchDateType = SearchDateType.CREATE;
		//documentType = DocumentType.TEXT;
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	public Date getSecondDate() {
		return secondDate;
	}
	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}
	public SearchDateMode getSearchDateMode() {
		return searchDateMode;
	}
	public void setSearchDateMode(SearchDateMode searchDateMode) {
		this.searchDateMode = searchDateMode;
	}
	public SearchDateType getSearchDateType() {
		return searchDateType;
	}
	public void setSearchDateType(SearchDateType searchDateType) {
		this.searchDateType = searchDateType;
	}
	public DocumentType getDocumentType() {
		return documentType;
	}
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
	public Integer getDocumentSize() {
		return documentSize;
	}
	public void setDocumentSize(Integer documentSize) {
		this.documentSize = documentSize;
	}
	public String getUserPseudo() {
		return userPseudo;
	}
	public void setUserPseudo(String userPseudo) {
		this.userPseudo = userPseudo;
	}
	
}
