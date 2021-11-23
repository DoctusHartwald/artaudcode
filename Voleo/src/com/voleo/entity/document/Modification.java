package com.voleo.entity.document;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.voleo.entity.user.User;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Modification {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Document targetDocument;
	
	private String name;
	
	@ManyToMany
	private Set<Tag> tags;
	
	@ManyToOne
	private User user;
	
	private String categories;

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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
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
	
	
}
