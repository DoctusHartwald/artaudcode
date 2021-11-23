package com.voleo.entity.backUp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.voleo.entity.document.Document;
import com.voleo.entity.document.Historique;
import com.voleo.entity.user.User;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BackUpModification{

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Document targetDocument;
	
	private String name;
	
	@ManyToOne
	private User user;
	
	private String categories;
	
	@OneToOne
	private Historique historique;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public Document getTargetDocument() {
		return targetDocument;
	}

	public void setTargetDocument(Document targetDocument) {
		this.targetDocument = targetDocument;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
	
	
}