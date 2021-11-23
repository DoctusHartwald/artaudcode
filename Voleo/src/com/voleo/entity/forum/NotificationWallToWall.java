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
public class NotificationWallToWall {
	
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
		private WallToWall wallToWall;

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

		public WallToWall getWallToWall() {
			return wallToWall;
		}

		public void setWallToWall(WallToWall wallToWall) {
			this.wallToWall = wallToWall;
		}
}
