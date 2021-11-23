package com.voleo.entity.document;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.voleo.entity.user.User;

@Entity
public class Commentaire {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Document document;
	
	private String textcommentaire;
	
	private Date createDate;

	private String notationCommentaire;

	@ManyToOne
	private User user;
	
	@ManyToMany
	@JoinTable(name="USERS_VOTE_COMS")
	private Collection<User> userWhoHasVoted;
	
	private Double mean = (double)0;
	private Long nbVote = (long)0;
	
	//Ajout pour le Graphe
	private String dateFormat;
	
	@OneToMany(mappedBy ="commentaire", 
			   cascade = CascadeType.PERSIST)
	private Set<NotificationCommentaire> notificationCommentaire;
	
	
	public Set<NotificationCommentaire> getNotificationCommentaire() {
		return notificationCommentaire;
	}
	
	public Collection<User> getUserWhoHasVoted() {
		return userWhoHasVoted;
	}

	public void setUserWhoHasVoted(Collection<User> userWhoHasVoted) {
		this.userWhoHasVoted = userWhoHasVoted;
	}
	
	public void setNotificationCommentaire(
			Set<NotificationCommentaire> notificationCommentaire) {
		this.notificationCommentaire = notificationCommentaire;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNotationCommentaire() {
		return notationCommentaire;
	}

	public void setNotationCommentaire(String notationCommentaire) {
		this.notationCommentaire = notationCommentaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTextcommentaire() {
		return textcommentaire;
	}

	public void setTextcommentaire(String textcommentaire) {
		this.textcommentaire = textcommentaire;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getMean() {
		return mean;
	}

	public void setMean(Double mean) {
		this.mean = mean;
	}
	
	public Long getNbVote() {
		return nbVote;
	}
	
	public void setNbVote(Long nbVote) {
		this.nbVote = nbVote;
	}
	
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
}
