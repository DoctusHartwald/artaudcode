package com.voleo.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.document.ICommentaireDAO;
import com.voleo.dao.user.IAdminDAO;
import com.voleo.dao.user.INewsDAO;
import com.voleo.dao.user.IUserDAO;
import com.voleo.dto.admin.DateCountCommentaire;
import com.voleo.entity.news.News;
import com.voleo.entity.user.Admin;
import com.voleo.entity.user.User;
import com.voleo.entity.user.UserType;


@Component
@Transactional
public class AdminService implements IAdminService{

	@Autowired
	private IAdminDAO adminDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private INewsDAO newsDAO;
	
	//a copier pour le graphe 
	@Autowired
	private ICommentaireDAO commentaireDAO ; 

	
	public Collection<DateCountCommentaire> getdateCountCommentaire() {
		return commentaireDAO.getdateCountCommentaire();
	}
	
	
	/*Fin Graph*/	
	
	////A COPIER Control PANEL

	
	public Collection<User> getAllTypeUser(){
		return userDAO.getAll();
	}
	////FIN COPIER 
	
	public Collection<User>getUserByPseudo(String pseudo){
		return userDAO.getUserByPseudo(pseudo);
	}
	
	public Collection<Admin> getAllAdmin(){
		return adminDAO.getAll();
	}
	/*public Collection<User> getAllTypeUser(){
		return userDAO.getAll();
	}*/
	
	public Collection<User> getAllTypeAdmin(){
		return userDAO.getAllTypeAdmin();
	}
	
	public void updateTypeUserToAdmin(Long userId){
		User user = userDAO.getById(userId);
		user.setUserType(UserType.ADMIN);
		userDAO.update(user);
	}
	
	
	
	public User getUserById(Long id){
		return userDAO.getById(id);
	}
	
	public void removeUser(Long userId){
		userDAO.removeById(userId);
	}
	
	public int documentCountByUSerId(Long userId){
		return userDAO.getCountDocByUserId(userId);
	}
	
	public int commentaireCountByUserId(Long userId){
		return userDAO.getCountCommentaireByUserId(userId);
	}
	
	
	
	public Collection<News> getAllNews(){
		return newsDAO.getAll();
	}
	
	public void addNews(News news){
		Date datecreation = new Date(); 
		news.setCreateDate(datecreation);
		newsDAO.add(news);
	}
	
	public void removeNews(Long newsId){
		newsDAO.removeById(newsId);
	}
	
	public News getNewsById(Long newsId){
		return newsDAO.getById(newsId);
	}
	
	public void updateNews(News news){
		 newsDAO.update(news);
	}
	

	
}
