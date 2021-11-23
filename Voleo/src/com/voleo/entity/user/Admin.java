package com.voleo.entity.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.voleo.entity.news.News;

@Entity
public class Admin {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String pseudo;
	
	private String telephonne;
	
	private String email;
	
	@OneToMany(mappedBy ="admin",cascade = CascadeType.PERSIST)
	private Set<News> news;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getTelephonne() {
		return telephonne;
	}

	public void setTelephonne(String telephonne) {
		this.telephonne = telephonne;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}
}
