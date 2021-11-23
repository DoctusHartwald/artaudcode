package com.voleo.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.forum.IForumDAO;
import com.voleo.dao.forum.INotificationForumDAO;
import com.voleo.dao.forum.INotificationWallToWallDAO;
import com.voleo.dao.forum.IReponseDAO;
import com.voleo.dao.user.IUserDAO;
import com.voleo.dto.admin.DateCountForum;
import com.voleo.entity.forum.Forum;
import com.voleo.entity.forum.NotificationForum;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.forum.Reponse;
import com.voleo.entity.user.User;

@Component
@Transactional
public class ForumService implements IForumService{
	

	@Autowired
	private IForumDAO forumDAO;
	
	@Autowired 
	private IReponseDAO reponseDAO;
	
	@Autowired 
	private IUserDAO userDAO;
	
	@Autowired 
	private INotificationForumDAO notificationForumDAO;
	

	public Collection<Forum> DiscussionSansReponse(){
		return forumDAO.DiscussionSansReponse();
	}
	
	
	public Collection<Forum> getAllDiscussion(){
		return forumDAO.getAllDiscussion();
	}
	
	public void addDiscussion(Forum forum){
		Date datecreation = new Date();
		forum.setCreateDate(datecreation);
		

		//Ajout pour le Graphe..
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String dateFormat2 = formatter.format(datecreation);
		forum.setDateFormat(dateFormat2);
		
		
		forumDAO.add(forum);
	}
	
	public Collection<Reponse> getResponseDiscussion(Long forumId){
		return reponseDAO.getResponseDiscussion(forumId);
	}
	
	public void addReponse(Reponse reponse, Long userOrigineId,Long forumId,Long userId){
		
		Date datecreation = new Date();
		reponse.setCreateDate(datecreation);
		
		User user = userDAO.getById(userId); //l'émetteur
		User userOrigine = userDAO.getById(userOrigineId); //le récepteur
		Forum forum = forumDAO.getById(forumId);
		
		reponse.setForum(forum);
		reponse.setUser(user);
		reponse.setUserOrigineForum(userOrigine);
		
		reponseDAO.add(reponse);
	}
	
	@Override
	public void addNotification(Reponse reponse, Long userOrigineId,Long forumId,Long userId){

		NotificationForum notificationForum = new NotificationForum();
		
		Date datecreation = new Date();
		notificationForum.setCreateDate(datecreation);
		
		Forum forum = forumDAO.getById(forumId);
		User userOrigine = userDAO.getById(userId);
		User userDestination = userDAO.getById(userOrigineId);
		
		notificationForum.setForum(forum);
		notificationForum.setUserDestination(userDestination);
		notificationForum.setUserOrigine(userOrigine);
		notificationForum.setReponse(reponse);
		
		NotificationWallToWall notificationWallToWall = new NotificationWallToWall();
		
		
		notificationForumDAO.add(notificationForum);
		
	}
	
	
	
	@Override
	public Forum getForumById(Long forumId){
		return forumDAO.getById(forumId);
	}
	
	@Override
	public Forum voirForumDiscussion(Long forumId){
		return forumDAO.getForumDiscussion(forumId);
	}
	
	@Override
	public int getCountAllReponseByForumId(Long forumId){
		return reponseDAO.getCountAllReponseByForumId(forumId);
	}
	@Override
	public int getCountDiscussionByForumId(){
		return forumDAO.getCountDiscussionByForumId();
	}
	
	@Override
	public int getCountNewDiscussionByForumId(){
		return forumDAO.getCountNewDiscussionByForumId();
	}
	
	@Override
	public Collection<Forum> myDiscussions(Long userId){
		return forumDAO.myDiscussions(userId);
	}
	
	@Override
	public Collection<NotificationForum> getNLastNotificationForum(int amount){
		return notificationForumDAO.getNLastNotificationForum(amount);
	}
	///Graph Part 
	@Override
	public Collection<DateCountForum> getDateCountForum() {
		return forumDAO.getDateCountForum();
	}
	/*Graph*/
	
}
