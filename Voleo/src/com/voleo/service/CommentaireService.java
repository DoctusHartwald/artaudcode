package com.voleo.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.document.ICommentaireDAO;
import com.voleo.dao.document.IDocumentDAO;
import com.voleo.dao.document.IImageDAO;
import com.voleo.dao.document.INotificationCommentaireDAO;
import com.voleo.dao.document.IRawFileDAO;
import com.voleo.dao.document.ITextDAO;
import com.voleo.dao.document.IVideoDAO;
import com.voleo.dao.user.IUserDAO;
import com.voleo.dto.admin.PseudoInfo;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.user.User;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class CommentaireService implements ICommentaireService{

	@Autowired
	private ICommentaireDAO commentaireDAO;
	
private Map<DocumentType, IDocumentDAO> daoMap; 
	
	@Autowired
	private ITextDAO textDAO;
	
	@Autowired
	private IImageDAO imageDAO;

	@Autowired
	private IVideoDAO videoDAO;

	@Autowired
	private IRawFileDAO rawFileDAO;
	
	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private INotificationCommentaireDAO notificationCommentaireDAO;
	
	@PostConstruct
	public void initDaoMap() {
		daoMap = new EnumMap<DocumentType, IDocumentDAO>(DocumentType.class);
		daoMap.put(DocumentType.TEXT, textDAO);
		daoMap.put(DocumentType.IMAGE, imageDAO);
		daoMap.put(DocumentType.VIDEO, videoDAO);
		daoMap.put(DocumentType.RAWFILE, rawFileDAO);
	}

	private IDocumentDAO getDAOForDocument(Document doc) {
		return daoMap.get(DocumentType.getByClass(doc.getClass()));
	}
	
	
	@Override
	public Collection<Commentaire> getAllCommentsByDocumentId(Long docId){
		
		return commentaireDAO.getAllCommentsByDocumentId(docId);
	}
	
	public void addCommentaire(Commentaire commentaire,DocumentType docType,Long docId,Long userId) {
		
		User user = userDAO.getById(userId);
		IDocumentDAO docDAO = daoMap.get(docType);
		Document document = (Document)docDAO.getById(docId);
		
		commentaire.setDocument(document);
		commentaire.setUser(user);
		Date datecreation = new Date(); 
		commentaire.setCreateDate(datecreation);
		

		//Ajout pour le Graphe..
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String dateFormat2 = formatter.format(datecreation);
		commentaire.setDateFormat(dateFormat2);
		
		
		commentaireDAO.add(commentaire);
	}
	
	public int getCountCommentBydocId(Long docId){
		return commentaireDAO.getCountCommentBydocId(docId);
	}
	
	@Override
	public Collection<User> getDocumentsVoteByUserIdComId(Long userId, Long comId){
		return commentaireDAO.getDocumentsVoteByUserIdComId(userId, comId);
	}
	
	@Override
	public Commentaire getCommentaireById(Long comId){
		return commentaireDAO.getCommentaireById(comId);
	}
	
	@Override
	public void ajoutNoteCommentaire(Commentaire com, int nouvelleNote){
		/* Calcul de la nouvelle moyenne du document */
		Double mean = com.getMean();
		if(mean!=0)
			mean = (mean+(double)nouvelleNote)/2.0;
		else
			mean = (double)nouvelleNote;
		com.setMean(mean);
		
		/* Incrémentation du nombre de vote */
		com.setNbVote(com.getNbVote()+1);
		
		/* Mise à jour du doc dans la base de données */
		commentaireDAO.update(com);
	}
	
	@Override
	public void voteCommentaire(Long userId,Commentaire com){
		/* 
		 * Récupère la liste de tous les documents que l'utilisateur à voté
		 * et met à jour dans la base de données
		 * 
		 */
		User user = userDAO.getById(userId);
		Collection<User> allUsersVote = commentaireDAO.getAllCommentairesVoteByUserId(com.getId());
		allUsersVote.add(user);
		com.setUserWhoHasVoted(allUsersVote);
		commentaireDAO.update(com);
	}
	
	@Override
	public void addNotificationCommentaire(Commentaire commentaire,DocumentType docType,Long docId,Long userId){
		NotificationCommentaire notificationCommentaire = new NotificationCommentaire();
		
		User user = userDAO.getById(userId);
		IDocumentDAO docDAO = daoMap.get(docType);
		Document document = (Document)docDAO.getById(docId);
		
		notificationCommentaire.setDocument(document);
		notificationCommentaire.setUser(user);
		Date datecreation = new Date(); 
		notificationCommentaire.setCreateDate(datecreation);
		notificationCommentaire.setCommentaire(commentaire);

		notificationCommentaireDAO.add(notificationCommentaire);
	}
	
	public Collection<NotificationCommentaire> getNLastNotificationCommentaire(int amount){
		return notificationCommentaireDAO.getNLastNotificationCommentaire(amount);
	}
	
	@Override
	public Collection<NotificationCommentaire> getAllNotificationCommentaire(){
		return notificationCommentaireDAO.getAll();
	}
	
	@Override
	public void deleteNotificationCommentaire(Long notificationCommentaireId){
		NotificationCommentaire notificationCommentaire = notificationCommentaireDAO.getById(notificationCommentaireId);
		notificationCommentaireDAO.remove(notificationCommentaire);
	}
	
	///graph
	@Override
	public Collection<PseudoInfo> surveillance(Long userId) {
		return commentaireDAO.surveillance(userId);
	}
	/*graph*/
}
