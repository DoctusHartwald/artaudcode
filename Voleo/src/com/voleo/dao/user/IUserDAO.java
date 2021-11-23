package com.voleo.dao.user;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.Document;
import com.voleo.entity.user.Contact;
import com.voleo.entity.user.ContactGroup;
import com.voleo.entity.user.User;

public interface IUserDAO extends IDAO<User> {
	public Collection<Contact> getContactsWithoutGroup(User user);
	
	public Collection<ContactGroup> getContactGroupsByUserId(Long userId);

	public User getByLoginAndPassword(String login, String password);
	
	public Collection<User>getAllTypeUser();
	public Collection<User>getAllTypeAdmin();
	public int getCountAllUser();
	public int getCountAllAdmin(); 
	
	/* modifié */
	public Collection<Document> getAllDocumentsVoteByUserId(Long userId);
	
	public Collection<Document> getDocumentsVoteByUserIdDocId(Long userId, Long docId);

	public Long getMaxHitComment();
	
	public Long getMaxHitArticle();
	
	public Long getMaxNbArticle();
	
	public Long getMaxNbAbusSignales();
	
	public Long getMaxNbComment();
	
	public int getCountCommentaireByUserId(Long userId);

	public int getCountDocByUserId(Long userId);
	
	public Collection<Document> getDocumentsVoteByUserIdExceptDocId(Long userId,
			Long docId);
	/* fin modifié */
	public Collection<User>getUserByPseudo(String pseudo);

	public Collection<Commentaire> getDocumentsVoteByUserIdComId(Long userId,
			Long comId);

	Collection<Commentaire> getAllCommentairesVoteByUserId(Long userId);
}
