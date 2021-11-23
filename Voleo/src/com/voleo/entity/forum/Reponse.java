package com.voleo.entity.forum;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.voleo.entity.user.User;

@Entity
public class Reponse {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String titre;
	
	private String answer;
	
	@ManyToOne
	private Forum forum;

	@ManyToOne
	private User user;
	
	@ManyToOne
	private User userOrigineForum;


	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="reponse",cascade = CascadeType.PERSIST)
	private Set<NotificationForum> notificationForumWallToWall;
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserOrigineForum() {
		return userOrigineForum;
	}

	public void setUserOrigineForum(User userOrigineForum) {
		this.userOrigineForum = userOrigineForum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<NotificationForum> getNotificationForumWallToWall() {
		return notificationForumWallToWall;
	}

	public void setNotificationForumWallToWall(
			Set<NotificationForum> notificationForumWallToWall) {
		this.notificationForumWallToWall = notificationForumWallToWall;
	}

	
}
