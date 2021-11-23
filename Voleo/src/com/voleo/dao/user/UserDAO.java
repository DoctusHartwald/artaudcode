package com.voleo.dao.user;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.Document;
import com.voleo.entity.user.Contact;
import com.voleo.entity.user.ContactGroup;
import com.voleo.entity.user.User;
import com.voleo.entity.user.UserType;


@Component
public class UserDAO extends AbstractDAO<User> implements IUserDAO {

	public UserDAO() {
		super(User.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User>getAllTypeUser(){
		return jpaTemplate.find("select c from User u " +
				"where u.userType = ?1",UserType.USER);  
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<User>getUserByPseudo(String pseudo){
		return jpaTemplate.find("from User u where u.pseudo = ?1",pseudo);  
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<User>getAllTypeAdmin(){
		return jpaTemplate.find("select c from User u " +
				"where u.userType = ?1",UserType.ADMIN);  
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Contact> getContactsWithoutGroup(User user) {
		return jpaTemplate.find("select c from User u inner join u.contacts c " +
				"where size(c.contactGroups) = 0 and u.id = ?1", user.getId());
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ContactGroup> getContactGroupsByUserId(Long userId) {
		return jpaTemplate.find("select cg from User u " +
				"left outer join u.contactGroups cg " +
				"left outer join fetch cg.contacts c " +
				"where u.id = ?1", userId);
	}

	@Override
	public int getCountAllUser() {
		return ((Long)jpaTemplate.find("select count(*) from User u where " +
				"u.userType = ?1", UserType.USER).get(0)).intValue();
	}

	@Override
	public int getCountDocByUserId(Long userId){
		return ((Long)jpaTemplate.find("select count(*) from Document d where " +
				"d.user.id = ?1", userId).get(0)).intValue();
	}
	
	

	@Override
	public int getCountCommentaireByUserId(Long userId){
		return ((Long)jpaTemplate.find("select count(*) from Commentaire c where " +
				"c.user.id = ?1", userId).get(0)).intValue();
	}
	
	@Override
	public int getCountAllAdmin() {
		return ((Long)jpaTemplate.find("select count(*) from User u where " +
				"u.userType = ?1", UserType.ADMIN).get(0)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getByLoginAndPassword(String login, String password) {
		List<User> results = 
			jpaTemplate.find("from User u where u.login = ?1 and u.password = ?2", 
				login, password);
		if (results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}
	
	/* modifié */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Commentaire> getDocumentsVoteByUserIdComId(Long userId,Long comId) {
		return jpaTemplate.find("select u.commentairesVote from User u " +
				"left outer join u.commentairesVote cv " +
				"where u.id = ?1 and cv.id = ?2 ", userId, comId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Document> getDocumentsVoteByUserIdDocId(Long userId,Long docId) {
		return jpaTemplate.find("select u.documentsVote from User u " +
				"left outer join u.documentsVote dv " +
				"where u.id = ?1 and dv.id = ?2 ", userId, docId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Document> getDocumentsVoteByUserIdExceptDocId(Long userId,Long docId) {
		return jpaTemplate.find("select u.documentsVote from User u " +
				"left outer join u.documentsVote dv " +
				"where u.id = ?1 and dv.id != ?2 ", userId, docId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Document> getAllDocumentsVoteByUserId(Long userId) {
		return jpaTemplate.find("select u.documentsVote from User u " +
				"where u.id = ?1 ", userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Commentaire> getAllCommentairesVoteByUserId(Long userId) {
		return jpaTemplate.find("select u.commentairesVote from User u " +
				"where u.id = ?1 ", userId);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Long getMaxHitArticle() {
		List<Long> max = jpaTemplate.find("select max(hitArticle) as max from User");
		return max.get(0);
	}


	@Override
	@SuppressWarnings("unchecked")
	public Long getMaxHitComment() {
		List<Long> max = jpaTemplate.find("select max(hitComment) as max from User");
		return max.get(0);
	}


	@Override
	@SuppressWarnings("unchecked")
	public Long getMaxNbAbusSignales() {
		List<Long> max = jpaTemplate.find("select max(nbAbusSignales) as max from User");
		return max.get(0);
	}


	@Override
	@SuppressWarnings("unchecked")
	public Long getMaxNbArticle() {
		List<Long> max = jpaTemplate.find("select max(nbArticle) as max from User");
		return max.get(0);
	}


	@Override
	@SuppressWarnings("unchecked")
	public Long getMaxNbComment() {
		List<Long> max = jpaTemplate.find("select max(nbComment) as max from User");
		return max.get(0);
	}
	/* fin modifié */
	
}
