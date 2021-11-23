package com.voleo.entity.forum;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.voleo.entity.user.User;

@Entity
public class NotificationForum {

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToOne
	private User userOrigine;
	
	@ManyToOne
	private User userDestination;
	
	@ManyToOne
	private Forum forum;
	
	@ManyToOne
	private Reponse reponse;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUserOrigine() {
		return userOrigine;
	}

	public void setUserOrigine(User userOrigine) {
		this.userOrigine = userOrigine;
	}

	public User getUserDestination() {
		return userDestination;
	}

	public void setUserDestination(User userDestination) {
		this.userDestination = userDestination;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Reponse getReponse() {
		return reponse;
	}

	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}

	
}
