package com.voleo.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.Action;
import com.voleo.entity.document.Document;
import com.voleo.entity.forum.Forum;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.forum.Reponse;
import com.voleo.entity.forum.WallToWall;
import com.voleo.entity.user.User;

import com.voleo.service.IForumService;
import com.voleo.service.IUserService;
import com.voleo.service.IWallToWallService;

@Component
public class ForumAction extends AbstractAction{
	
	
	@Autowired
	private IForumService forumService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired 
	private IWallToWallService wallToWallService;
	
	private Collection<Forum> allDiscussions;
	private Forum forum;
	private Long forumId;
	private Long userId;
	private Long userOrigine;
	private Long userDestination;
	private Collection<Reponse> responseByDiscussion;
	private Reponse reponse;
	private WallToWall wallToWall;
	private Collection<WallToWall> getMyReceivedWallToWall;
	private Forum viewForumDiscussion;
	private Long wallToWallId;
	private Collection<Forum> mesDiscussions;
	private NotificationWallToWall notificationWallToWall;
	private Collection<Forum> listDiscussionSansReponse;
	
	public String getForumDiscussionByForumId(){
		viewForumDiscussion = forumService.getForumById(forumId);
		viewForumDiscussion.setCountReponse(forumService.getCountAllReponseByForumId(forumId));
		//viewForumDiscussion = forumService.voirForumDiscussion(forumId);
		return Action.SUCCESS;
	}
	
	
	public String listDiscussion(){
		allDiscussions = forumService.getAllDiscussion();
		for(Forum i: allDiscussions){
			i.setCountReponse(forumService.getCountAllReponseByForumId(i.getId()));
		}
		return Action.SUCCESS;
	}

	public String addUpdateDiscussionForm(){
		forum = new Forum();
		return Action.SUCCESS;
	}
	
	public String addDiscussionForum(){
		Long userId = getUserIdInSession();
		User user = userService.getUserById(userId);
		forum.setUser(user);
		forumService.addDiscussion(forum);
		return listDiscussion();
	}
	
	public String listResponseDiscussion(){
		
		responseByDiscussion = forumService.getResponseDiscussion(forumId);
		
		return Action.SUCCESS;
	}
	

	public String addReponseForm(){
		reponse = new Reponse();
		return Action.SUCCESS;
	}
	
	public String addReponse(){
		Long userOrigineId = userId;
		Long userIdRepondant = getUserIdInSession();
		forumService.addReponse(reponse, userOrigineId, forumId, userIdRepondant);
		forumService.addNotification(reponse, userOrigineId, forumId, userIdRepondant);
		return listResponseDiscussion();
	}
	
	public String sendWallToWallForm(){
		Long userId = getUserIdInSession();
		
		notificationWallToWall = new NotificationWallToWall();
		wallToWall = new WallToWall();
		
		wallToWall.setUserOrigine(userService.getUserById(userId));
		wallToWall.setUserDestination(userService.getUserById(userDestination));
		wallToWall.setForum(forumService.getForumById(forumId));
		
		notificationWallToWall.setUserOrigine(userService.getUserById(userId));
		notificationWallToWall.setUserDestination(userService.getUserById(userDestination));
		notificationWallToWall.setForum(forumService.getForumById(forumId));
		
		return Action.SUCCESS;
	}
	
	public String sendWallToWall(){
		
		wallToWallService.addWallToWall(wallToWall);
		wallToWallService.addNotificationWallToWall(notificationWallToWall);
		return listDiscussion();
		
	}
	
	public String receivedWallToWall(){
		Long userId = getUserIdInSession();
		getMyReceivedWallToWall = wallToWallService.getAllMyReceivedWallToWall(userId);
		return Action.SUCCESS;
	}
	
	public String deleteWallToWall(){
		wallToWallService.deleteWallToWall(wallToWallId);
		return receivedWallToWall();
	}
	
	public String myDiscussions(){
		Long userId = getUserIdInSession();
		mesDiscussions = forumService.myDiscussions(userId);
		for(Forum i: mesDiscussions){
			i.setCountReponse(forumService.getCountAllReponseByForumId(i.getId()));
		}
	return Action.SUCCESS;
	}
	
	public String getWallToWallByWallToWallId(){
		wallToWall = wallToWallService.getWallToWallById(wallToWallId);
		return Action.SUCCESS;
	}
	
	public String discussionSansReponse(){
		listDiscussionSansReponse = forumService.DiscussionSansReponse();
		return Action.SUCCESS;
	}
	
	
	public Collection<Forum> getAllDiscussions() {
		return allDiscussions;
	}

	public void setAllDiscussions(Collection<Forum> allDiscussions) {
		this.allDiscussions = allDiscussions;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Reponse getReponse() {
		return reponse;
	}

	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}

	public Collection<Reponse> getResponseByDiscussion() {
		return responseByDiscussion;
	}

	public void setResponseByDiscussion(Collection<Reponse> responseByDiscussion) {
		this.responseByDiscussion = responseByDiscussion;
	}

	public Long getUserOrigine() {
		return userOrigine;
	}

	public void setUserOrigine(Long userOrigine) {
		this.userOrigine = userOrigine;
	}

	public Long getUserDestination() {
		return userDestination;
	}

	public void setUserDestination(Long userDestination) {
		this.userDestination = userDestination;
	}

	public WallToWall getWallToWall() {
		return wallToWall;
	}

	public void setWallToWall(WallToWall wallToWall) {
		this.wallToWall = wallToWall;
	}


	public Collection<WallToWall> getGetMyReceivedWallToWall() {
		return getMyReceivedWallToWall;
	}


	public void setGetMyReceivedWallToWall(
			Collection<WallToWall> getMyReceivedWallToWall) {
		this.getMyReceivedWallToWall = getMyReceivedWallToWall;
	}


	public Forum getViewForumDiscussion() {
		return viewForumDiscussion;
	}


	public void setViewForumDiscussion(Forum viewForumDiscussion) {
		this.viewForumDiscussion = viewForumDiscussion;
	}


	public Long getWallToWallId() {
		return wallToWallId;
	}


	public void setWallToWallId(Long wallToWallId) {
		this.wallToWallId = wallToWallId;
	}


	public Collection<Forum> getMesDiscussions() {
		return mesDiscussions;
	}


	public void setMesDiscussions(Collection<Forum> mesDiscussions) {
		this.mesDiscussions = mesDiscussions;
	}


	public NotificationWallToWall getNotificationWallToWall() {
		return notificationWallToWall;
	}


	public void setNotificationWallToWall(
			NotificationWallToWall notificationWallToWall) {
		this.notificationWallToWall = notificationWallToWall;
	}


	public Collection<Forum> getListDiscussionSansReponse() {
		return listDiscussionSansReponse;
	}


	public void setListDiscussionSansReponse(
			Collection<Forum> listDiscussionSansReponse) {
		this.listDiscussionSansReponse = listDiscussionSansReponse;
	}



	
	
	
}
