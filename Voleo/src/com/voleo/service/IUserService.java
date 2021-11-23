package com.voleo.service;

import java.util.Collection;

import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.Document;
import com.voleo.entity.user.User;

public interface IUserService {
	public void addUser(User user);
	
	public void removeUser(User user);
	
	public User login(String login, String password);
	
	public User getUserById(Long userId);

	public void update(User user);
	
	public Collection<User> getAllUser();
	
	public int getCountAllUser();
	
	public int getCountAllAdmin(); 
	/* modifié */

	public void ajoutNouvelleNoteArticle(int mark, Long userId);

	public Double getUserSMarkById(Long userId);

	void supprimerArticle(Long userId, Document doc);
	/* fin modifié */

	public void ajoutNouvelleNoteCommentaire(int mark, Long userId);

	void voteArticle(Long userId, Document doc);

	void voteCommentaire(Long userId, Commentaire com);

	boolean aVoteArticle(Long userId, Document doc);

	boolean aVoteCommentaire(Long userId, Commentaire com);

	void ajoutArticle(Long userId);
	
	void ajoutCommentaire(Long userId);
	
}
