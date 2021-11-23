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
public class WallToWall {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String titre;
	
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToOne
	private Forum forum;
	
	private int view;
	
	@ManyToOne
	private User userOrigine;
	
	@ManyToOne
	private User userDestination;

	@OneToMany(mappedBy ="wallToWall",cascade = CascadeType.PERSIST)
	private Set<NotificationWallToWall> notificationWallToWall;
	
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

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
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

	public Set<NotificationWallToWall> getNotificationWallToWall() {
		return notificationWallToWall;
	}

	public void setNotificationWallToWall(
			Set<NotificationWallToWall> notificationWallToWall) {
		this.notificationWallToWall = notificationWallToWall;
	}


}
