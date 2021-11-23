package com.voleo.dto.search;

import java.util.Date;
import java.util.List;

import com.voleo.entity.document.DocumentType;
import com.voleo.entity.user.User;

public class SearchResult {
	private String name;
	private Date createDate;
	private Date updateDate;
	private Long id;
	private List<String> tags;
	private String userPseudo;
	private User user;
	private DocumentType documentType;
	private float score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getUserPseudo() {
		return userPseudo;
	}
	public void setUserPseudo(String userPseudo) {
		this.userPseudo = userPseudo;
	}
	public DocumentType getDocumentType() {
		return documentType;
	}
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
