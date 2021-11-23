package com.voleo.service;

import java.util.Collection;

import com.voleo.dto.admin.DateCountCommentaire;
import com.voleo.entity.news.News;
import com.voleo.entity.user.Admin;
import com.voleo.entity.user.User;

public interface IAdminService {

	public Collection<Admin> getAllAdmin();
	public Collection<User> getAllTypeUser();
	public Collection<User> getAllTypeAdmin();
	public User getUserById(Long id);
	public void removeUser(Long userId);
	public void updateTypeUserToAdmin(Long userId);

	
	public void addNews(News news);
	public Collection<News> getAllNews();
	public void removeNews(Long newsId);
	public News getNewsById(Long newsId);
	public void updateNews(News news);
	public int documentCountByUSerId(Long userId);
	public int commentaireCountByUserId(Long userId);
	public Collection<User>getUserByPseudo(String pseudo);
	//graphique : à copier 
	public Collection<DateCountCommentaire> getdateCountCommentaire();

}
