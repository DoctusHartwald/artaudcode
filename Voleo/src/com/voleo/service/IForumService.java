package com.voleo.service;

import java.util.Collection;

import com.voleo.dto.admin.DateCountForum;
import com.voleo.entity.forum.Forum;
import com.voleo.entity.forum.NotificationForum;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.forum.Reponse;

public interface IForumService {
	
	public Collection<Forum> getAllDiscussion();
	public void addDiscussion(Forum forum);
	public Collection<Reponse> getResponseDiscussion(Long forumId);
	public void addReponse(Reponse reponse, Long userOrigineId,Long forumId,Long userId);
	public Forum getForumById(Long forumId);
	public Forum voirForumDiscussion(Long forumId);
	public int getCountAllReponseByForumId(Long forumId);
	public int getCountDiscussionByForumId();
	public int getCountNewDiscussionByForumId();
	public Collection<Forum> myDiscussions(Long userId);
	public void addNotification(Reponse reponse, Long userOrigineId,Long forumId,Long userId);
	public Collection<NotificationForum> getNLastNotificationForum(int amount);
	public Collection<Forum> DiscussionSansReponse();
	//graph Part 
	public Collection<DateCountForum> getDateCountForum();
	/**/
}
