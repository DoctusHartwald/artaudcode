package com.voleo.entity.forum;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;

import com.voleo.entity.user.User;

@Entity
public class Forum {

	@Id
	@GeneratedValue
	private Long id;
	
	private String titre;
	private String message;
	
	@OneToMany(mappedBy ="forum",cascade = CascadeType.PERSIST)
	private Set<Reponse> reponse;
	
	@OneToMany(mappedBy ="forum",cascade = CascadeType.PERSIST)
	private Set<NotificationWallToWall> notificationWallToWall;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	private int notationForum;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy ="forum",cascade = CascadeType.PERSIST)
	private Set<WallToWall> wallToWall;

	@OneToMany(fetch = FetchType.EAGER, mappedBy ="forum",cascade = CascadeType.PERSIST)
	private Set<NotificationForum> NotificationForumforum;
	
	//Ajout pour le Graphe
	private String dateFormat;
	
	
	private int countReponse;
	
	public int getCountReponse() {
		return countReponse;
	}

	public void setCountReponse(int countReponse) {
		this.countReponse = countReponse;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNotationForum() {
		return notationForum;
	}

	public void setNotationForum(int notationForum) {
		this.notationForum = notationForum;
	}

	public Set<Reponse> getReponse() {
		return reponse;
	}

	public void setReponse(Set<Reponse> reponse) {
		this.reponse = reponse;
	}

	public Set<WallToWall> getWallToWall() {
		return wallToWall;
	}

	public void setWallToWall(Set<WallToWall> wallToWall) {
		this.wallToWall = wallToWall;
	}

	public Set<NotificationForum> getNotificationForumforum() {
		return NotificationForumforum;
	}

	public void setNotificationForumforum(
			Set<NotificationForum> notificationForumforum) {
		NotificationForumforum = notificationForumforum;
	}

	public Set<NotificationWallToWall> getNotificationWallToWall() {
		return notificationWallToWall;
	}

	public void setNotificationWallToWall(
			Set<NotificationWallToWall> notificationWallToWall) {
		this.notificationWallToWall = notificationWallToWall;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}







}
