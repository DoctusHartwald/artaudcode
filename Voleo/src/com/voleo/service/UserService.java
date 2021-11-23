package com.voleo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.user.IUserDAO;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Text;
import com.voleo.entity.user.User;
import com.voleo.entity.user.UserType;


@Component
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public void addUser(User user) {
		user.setUserType(UserType.USER);
		userDAO.add(user);
	}

	@Override
	public User getUserById(Long userId) {
		return userDAO.getById(userId);
	}

	@Override
	public User login(String login, String password) {
		return userDAO.getByLoginAndPassword(login, password);
	}

	@Override
	public void removeUser(User user) {
		userDAO.remove(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}
	
	@Override
	public Collection<User> getAllUser(){
		return userDAO.getAll();
	}
	
	@Override
	public int getCountAllUser(){
		return userDAO.getCountAllUser();
	}

	@Override
	public int getCountAllAdmin() {
		return userDAO.getCountAllAdmin();
	}
	
	/* modifié */
	@Override
	public Double getUserSMarkById(Long userId) {
		User user = userDAO.getById(userId);
		Double maxHitArticle = (double)userDAO.getMaxHitArticle();
		Double maxHitComment = (double)userDAO.getMaxHitComment();
		Double maxNbArticle = (double)userDAO.getMaxNbArticle();
		Double maxNbComment = (double)userDAO.getMaxNbComment();
		Double coeff1 =(double)0;
		Double coeff2 =(double)0;
		Double coeff3 =(double)0;
		Double coeff4 =(double)0;
		if(maxHitArticle!=0.0)
			coeff1 = (double)user.getHitArticle() / maxHitArticle;
		System.out.println("coeff1:"+coeff1);
		if(maxHitComment!=0.0)
			coeff2 = (double)user.getHitComment() / maxHitComment;
		System.out.println("coeff2:"+coeff2);
		if(maxNbArticle!=0.0)
			coeff3 = (double)user.getNbArticle() / maxNbArticle;
		System.out.println("coeff3:"+coeff3);
		if(maxNbComment!=0.0)
			coeff4 = (double)user.getNbComment() / maxNbComment;
		System.out.println("coeff4:"+coeff4);
		//Double coeff5 = (double)user.getNbAbusSignales() / (double)userDAO.getMaxNbAbusSignales();
		Double mark = 0.2*(coeff1+coeff2+coeff3+coeff3+coeff4) + 0.8*user.getMeanComment() + user.getMeanArticle();		
		return mark;
	}
	
	private void processNewMeanArticle(int mark, User user) {
		Double newMean;
		if(user.getMeanArticle()!= 0)
			newMean = (user.getMeanArticle() + (double)mark)/2.0;
		else
			newMean = (double)mark;
		user.setMeanArticle(newMean);
	}

	private void processNewMeanComment(int mark, User user) {
		Double newMean;
		if(user.getMeanComment()!= 0)
			newMean = (user.getMeanComment() + (double)mark)/2.0;
		else
			newMean = (double)mark;
		user.setMeanComment(newMean);
	}
	
	@Override
	public void voteArticle(Long userId,Document doc){
		/* 
		 * Récupère la liste de tous les documents que l'utilisateur à voté
		 * et met à jour dans la base de données
		 * 
		 */
		User user = userDAO.getById(userId);
		Collection<Document> allDocumentsVote = userDAO.getAllDocumentsVoteByUserId(userId);
		allDocumentsVote.add(doc);
		user.setDocumentsVote(allDocumentsVote);
		userDAO.update(user);
	}
	
	@Override
	public void voteCommentaire(Long userId,Commentaire com){
		/* 
		 * Récupère la liste de tous les documents que l'utilisateur à voté
		 * et met à jour dans la base de données
		 * 
		 */
		User user = userDAO.getById(userId);
		Collection<Commentaire> allCommentairesVote = userDAO.getAllCommentairesVoteByUserId(userId);
		allCommentairesVote.add(com);
		user.setCommentairesVote(allCommentairesVote);
		userDAO.update(user);
	}
	
	@Override
	public boolean aVoteArticle(Long userId, Document doc){
		/* Vérifier si l'utilisateur n'a pas déjà voté */
		/* Rq: avec contains(doc), ca passe à travers et je ne sais pas pour quelle raison */
		
		Collection<Document> documentsVote = userDAO.getDocumentsVoteByUserIdDocId(userId,doc.getId());
		if(!documentsVote.isEmpty())
			return true;
		return false;
	}
	
	@Override
	public boolean aVoteCommentaire(Long userId, Commentaire com){
		/* Vérifier si l'utilisateur n'a pas déjà voté */
		/* Rq: avec contains(doc), ca passe à travers et je ne sais pas pour quelle raison */
		
		Collection<Commentaire> commentairesVote = userDAO.getDocumentsVoteByUserIdComId(userId,com.getId());
		if(!commentairesVote.isEmpty())
			return true;
		return false;
	}
	@Override
	public void ajoutNouvelleNoteArticle(int mark, Long userId){
		User user = userDAO.getById(userId);
		user.setHitArticle(user.getHitArticle()+1);
		processNewMeanArticle(mark,user);
		userDAO.update(user);
	}
	
	@Override
	public void ajoutNouvelleNoteCommentaire(int mark, Long userId){
		User user = userDAO.getById(userId);
		user.setHitComment(user.getHitComment()+1);
		processNewMeanComment(mark,user);
		userDAO.update(user);
	}
	
	@Override
	public void ajoutArticle(Long userId){
		User user = userDAO.getById(userId);
		user.setNbArticle(user.getNbArticle()+1);
		userDAO.update(user);
	}
	
	@Override
	public void ajoutCommentaire(Long userId){
		User user = userDAO.getById(userId);
		user.setNbComment(user.getNbComment()+1);
		userDAO.update(user);
	}
	
	@Override
	public void supprimerArticle(Long userId,Document doc){
		User user = userDAO.getById(userId);
		
		Long hitArticle = user.getHitArticle();
		hitArticle -= doc.getNbVote();
		if(hitArticle < 0)
			hitArticle = (long)0;
		user.setHitArticle(hitArticle);
		
		Long nbArticle = user.getNbArticle();
		nbArticle--;
		if(nbArticle < 0)
			nbArticle = (long)0;
		user.setNbArticle(nbArticle);
		
		/* 
		 * a faire:
		 * 		- retiré les votes des commentaires de tout l'article
		 * 		- mettre à jour la liste des dejaVoté des commentaires
		 * 		- retiré le nombre de commentaires des commentaires supprimés de chaque proprio
		 */
		
		user.setDocumentsVote(userDAO.getDocumentsVoteByUserIdExceptDocId(userId, doc.getId()));
		
		userDAO.update(user);
	}
	/* fin modifié */
	
}
