package com.voleo.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.backUp.IBackUpModificationDAO;
import com.voleo.dao.backUp.ITextBackUpModificationDAO;
import com.voleo.dao.document.IDocumentDAO;
import com.voleo.dao.document.IHistoriqueDAO;
import com.voleo.dao.document.IImageDAO;
import com.voleo.dao.document.IImageModificationDAO;
import com.voleo.dao.document.IModificationDAO;
import com.voleo.dao.document.INotificationCommentaireDAO;
import com.voleo.dao.document.IRawFileDAO;
import com.voleo.dao.document.IRawFileModificationDAO;
import com.voleo.dao.document.ITagDAO;
import com.voleo.dao.document.ITextDAO;
import com.voleo.dao.document.ITextModificationDAO;
import com.voleo.dao.document.IVideoDAO;
import com.voleo.dao.document.IVideoModificationDAO;
import com.voleo.dao.user.IUserDAO;
import com.voleo.dto.admin.DateCountDocument;
import com.voleo.dto.admin.DateCountImage;
import com.voleo.dto.admin.DateCountVideo;
import com.voleo.entity.backUp.BackUpModification;
import com.voleo.entity.backUp.TextBackUpModification;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentStatus;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.Historique;
import com.voleo.entity.document.Modification;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.document.Tag;
import com.voleo.entity.document.Text;
import com.voleo.entity.document.TextModification;
import com.voleo.entity.user.User;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class DocumentService implements IDocumentService {

	private Map<DocumentType, IDocumentDAO> docDaoMap; 
	private Map<DocumentType, IModificationDAO> modDaoMap; 
	private Map<DocumentType, IBackUpModificationDAO> backUpModDaoMap;
	
	@Autowired
	private ITagDAO tagDAO;
	
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
	private ITextModificationDAO textModificationDAO;

	@Autowired
	private IImageModificationDAO imageModificationDAO;
	
	@Autowired
	private IVideoModificationDAO videoModificationDAO;
	
	@Autowired
	private IRawFileModificationDAO rawFileModificationDAO;
	
	@Autowired
	private ITextBackUpModificationDAO textBackUpModificationDAO;

	@Autowired 
	private IHistoriqueDAO historiqueDAO;
	
	@Autowired
	private INotificationCommentaireDAO notificationCommentaireDAO;
	
	@PostConstruct
	public void initDaoMap() {
		docDaoMap = new EnumMap<DocumentType, IDocumentDAO>(DocumentType.class);
		docDaoMap.put(DocumentType.TEXT, textDAO);
		docDaoMap.put(DocumentType.IMAGE, imageDAO);
		docDaoMap.put(DocumentType.VIDEO, videoDAO);
		docDaoMap.put(DocumentType.RAWFILE, rawFileDAO);

		modDaoMap = new EnumMap<DocumentType, IModificationDAO>(DocumentType.class);
		modDaoMap.put(DocumentType.TEXT, textModificationDAO);
		modDaoMap.put(DocumentType.IMAGE, imageModificationDAO);
		modDaoMap.put(DocumentType.VIDEO, videoModificationDAO);
		modDaoMap.put(DocumentType.RAWFILE, rawFileModificationDAO);
		
		backUpModDaoMap = new EnumMap<DocumentType, IBackUpModificationDAO>(DocumentType.class);
		backUpModDaoMap.put(DocumentType.TEXT, textBackUpModificationDAO);
//		backUpModDaoMap.put(DocumentType.IMAGE, imageBackUpModificationDAO);	
//		backUpModDaoMap.put(DocumentType.VIDEO, videoBackUpModificationDAO);	
//		backUpModDaoMap.put(DocumentType.RAWFILE, rawFileBackUpModificationDAO);	

	}

	private IDocumentDAO getDocDAOForDocument(Document doc) {
		return docDaoMap.get(DocumentType.getByClass(doc.getClass()));
	}

	private IModificationDAO getModDAOForDocument(Document doc) {
		return modDaoMap.get(DocumentType.getByClass(doc.getClass()));
	}	
	
	private IBackUpModificationDAO getBackUpModDAOForDocument(Document doc) {
		return backUpModDaoMap.get(DocumentType.getByClass(doc.getClass()));
	}
	

	@Override
	public void addDocument(Document doc, Long userId, String[] tagNames) {
		User user = userDAO.getById(userId);
		doc.setUser(user);
		
		Set<Tag> tags = new HashSet<Tag>();
		for (String tagName : tagNames) {
			tagName = tagName.trim().toLowerCase();
			Tag tag = tagDAO.getByName(tagName);
			if (tag == null) {
				tag = new Tag();
				tag.setName(tagName);
				tagDAO.add(tag);
			}
			tags.add(tag);
		}
		doc.setTags(tags);
		
		doc.setStatus(DocumentStatus.VALIDATED);
		Date datecreation = new Date(); 
		doc.setCreateDate(datecreation);
		

		//Ajout pour le Graphe..
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String dateFormat2 = formatter.format(datecreation);
		doc.setDateFormat(dateFormat2);
		
		
		IDocumentDAO docDAO = getDocDAOForDocument(doc);
		docDAO.add(doc);
	}


	@Override
	public Document getDocument(DocumentType docType, Long docId) {
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return (Document)docDAO.getById(docId);
	}

	@Override
	public Modification getModification(DocumentType docType, Long modId) {
		IModificationDAO modDAO = modDaoMap.get(docType);
		return (Modification)modDAO.getById(modId);
	}
	
	
	public BackUpModification getBackUpDocument(DocumentType docType,Long historiqueId){
		IBackUpModificationDAO backUpDAO = backUpModDaoMap.get(docType);
		return (BackUpModification)backUpDAO.getBackUpModificationByHistoriqueId(historiqueId);
		//return  (BackUpModification)backUpDAO.getById(docId);
	}
	
	@Override
	public void removeDocument(DocumentType docType, Long docId) {
		IDocumentDAO docDAO = docDaoMap.get(docType);
		/*Document toRemove = (Document)docDAO.getById(docId);
		toRemove.setNotificationCommentaire(null);
		docDAO.update(toRemove);
		Set<NotificationCommentaire> nc = toRemove.getNotificationCommentaire();
		for(NotificationCommentaire i : nc){
			System.out.println("ici "+i.getId());
			notificationCommentaireDAO.remove(i);
		}*/
		docDAO.removeById(docId);		
	}

	@Override
	public void updateDocument(Document doc, Long userId) {
		IDocumentDAO docDAO = getDocDAOForDocument(doc);
		
		//si c'est le créateur du doc
		if (doc.getUser().getId().equals(userId)) {
			docDAO.update(doc);
		} else { 
		//si c'est un utilisateur externe(autre que le créateur) qui a effectué la modification
			IModificationDAO modDAO = getModDAOForDocument(doc);
			modDAO.add(doc.getModification());
		}
	}
	
	
	public void modificationDecision(DocumentType docType, Long modId, boolean approve) {
		IModificationDAO modDAO = modDaoMap.get(docType);
		Modification modification = (Modification)modDAO.getById(modId);
		
		if (approve) {
			IDocumentDAO docDAO = 
				getDocDAOForDocument(modification.getTargetDocument());
			Document doc = 
				(Document)docDAO.getById(modification.getTargetDocument().getId());
			mergeModification(doc, modification);
			
	
			IBackUpModificationDAO backUpDAO = getBackUpModDAOForDocument(doc);
			BackUpModification backUpModification = null;
			
			if(docType.equals(DocumentType.TEXT)){
				TextBackUpModification textBackUp = new TextBackUpModification();
				textBackUp.setText(((Text)doc).getText());
				backUpModification = textBackUp;	
			}
			backUpModification.setName(doc.getName());
			backUpModification.setUser(modification.getUser());
			
			backUpModification.setTargetDocument(doc);
			Historique historique = addHistorique(doc.getUser(),modification.getUser(),doc);
			backUpModification.setHistorique(historique);
			
			backUpDAO.add(backUpModification);
			System.out.println("User Pseudo"+doc.getUser().getPseudo());
			docDAO.update(doc);
		}
		modDAO.removeById(modId);
	}
	
	private Historique addHistorique(User userOrigine,User userHistorique,Document doc){
		
		Historique historique = new Historique();
		historique.setDocument(doc);
		historique.setUserOrigine(userOrigine); // User originaire du doc
		historique.setUser(userHistorique); //User ayant modifié le doc
		
		Date datecreation = new Date(); 
		historique.setCreateDate(datecreation);
		
		historiqueDAO.add(historique);
		
		return historique;
	}
	
	public void addBackUpModification(DocumentType docType,Document doc){
		IBackUpModificationDAO backUpDAO = getBackUpModDAOForDocument(doc);
		BackUpModification backUpModification = new BackUpModification();
		
		if(docType.equals(DocumentType.TEXT)){
			TextBackUpModification textBackUp = new TextBackUpModification();
			textBackUp.setText(((Text)doc).getText());
			backUpModification = (BackUpModification)textBackUp;	
		}
		backUpModification.setName(doc.getName());
		backUpModification.setUser(doc.getUser());
		backUpModification.setTargetDocument(doc);
		
		backUpDAO.add(backUpModification);
	}
	
	private void mergeModification(Document doc, Modification mod) {
		if (mod.getCategories() != null) {
			doc.setCategories(mod.getCategories());
		}
		if (mod.getName() != null) {
			doc.setName(mod.getName());
		}
		if (mod.getTags() != null) {
			doc.setTags(mod.getTags());
		}
		/*if( mod.getUser() != null){
			doc.setUser(mod.getUser());
		}*/
		if (doc instanceof Text && ((TextModification)mod).getText() != null) {
			((Text)doc).setText(((TextModification)mod).getText());
		}
	}
//
//	private <T extends Document> T cloneDocument(T doc) {
//		T newDoc = null;
//		try {
//			newDoc = (T)doc.getClass().newInstance();
//		} catch (Exception e) {
//			// Should not occurs
//		}
//		newDoc.setName(doc.getName());
//		newDoc.setUser(doc.getUser());
//		newDoc.setCommentaires(new HashSet<Commentaire>(doc.getCommentaires()));
//		newDoc.setCategories(doc.getCategories());
//		newDoc.setTags(new HashSet<Tag>(doc.getTags()));
//		newDoc.setCreateDate(doc.getCreateDate());
//		newDoc.setUpdateDate(doc.getUpdateDate());
//		
//		if (doc instanceof Text) {
//			((Text)newDoc).setText(((Text)doc).getText());
//		}
//		return newDoc;
//	}
	
	@Override
	public Collection<Document> getAllValidated(DocumentType docType, Long userId) {
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return docDAO.getAllValidated(userId);	
	}
	
	@Override
	public Collection<Modification> getAllPendingModification(DocumentType docType, Long userId) {
		IModificationDAO modDAO = modDaoMap.get(docType);
		return modDAO.getByTargetDocumentUserId(userId);
	}
	
	@Override
	public Collection<Document> getLastNMyDocument(DocumentType docType, Long userId,
			int amount) {
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return docDAO.getNLastByUser(userId, amount);
	}

	@Override
	public int getMyDocumentCount(DocumentType docType, Long userId) {
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return docDAO.getCountByUser(userId);
	}
	
	@Override
	public int getCountAllByUser(Long userId) {
		IDocumentDAO docDAO = docDaoMap.get(DocumentType.TEXT);
		return docDAO.getCountAllByUser(userId);
	}
	
	@Override
	public int getCountPendingByUserId(DocumentType docType,Long userId) {
		IModificationDAO modDAO = modDaoMap.get(docType);
		return modDAO.getCountPendingByUserId(userId);
	}
	
	@Override
	public int getCountAllPendingByUserId(Long userId) {
		IModificationDAO modDAO = modDaoMap.get(DocumentType.TEXT);
		return modDAO.getCountAllPendingByUserId(userId);	
	}
	
	@Override
	public Collection<Document> getAllMyDocument(DocumentType docType, Long userId) {
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return docDAO.getAllByUserId(userId);
	}
	
	@Override
	public Collection<Document> getAllDocument(DocumentType docType,Long userId){
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return docDAO.getAllOtherDocument(userId);
	}
	
	@Override
	public Collection<Document> getNLastDocument(DocumentType docType, int amount) {
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return docDAO.getNLastDocument(amount);
	}
	
	@Override
	public byte[] getImageContent(Long docId) {
		return imageDAO.getById(docId).getImage();
	}

	@Override
	public byte[] getDocContent(Long docId) {
		return rawFileDAO.getById(docId).getContent();
	}
	
	@Override
	public byte[] getVideoContent(Long docId) {
		return videoDAO.getById(docId).getVideo();
	}
	
	
	
	///Ajout pour le Graphique 
	@Override
	public  Collection<DateCountDocument>  getDateCountDocument() {
		IDocumentDAO docDAO = docDaoMap.get(DocumentType.TEXT);	
		return docDAO.getDateCountDocument();	
	}

	@Override
	public Collection<DateCountImage> getDateCountImage() {
		IDocumentDAO docDAO = docDaoMap.get(DocumentType.IMAGE);	
		return docDAO.getDateCountImage();
		//return docDAO.getDateCountDocument();
	}

	@Override
	public Collection<DateCountVideo> getDateCountVideo() {
		IDocumentDAO docDAO = docDaoMap.get(DocumentType.VIDEO);
		return docDAO.getDateCountVideo();
	}
	
	
	/////////Fin copie 
	
	
	
	
	
	
	
	
	/* modifié */
	@Override
	public void voteArticle(Long userId,Document doc){
		/* 
		 * Récupère la liste de tous les documents que l'utilisateur à voté
		 * et met à jour dans la base de données
		 * 
		 */
		User user = userDAO.getById(userId);
		IDocumentDAO docDAO = docDaoMap.get(DocumentType.valueOf(doc.getType()));
		Collection<User> allUsersVote = docDAO.getDocumentsVoteByUserIdDocId(userId, doc.getId());
		allUsersVote.add(user);
		doc.setUserWhoHasVoted(allUsersVote);
		docDAO.update(doc);
	}
	
	@Override
	public void ajoutNoteArticle(Document doc, int nouvelleNote){
		/* Calcul de la nouvelle moyenne du document */
		Double mean = doc.getMean();
		if(mean!=0)
			mean = (mean+(double)nouvelleNote)/2.0;
		else
			mean = (double)nouvelleNote;
		doc.setMean(mean);
		
		/* Incrémentation du nombre de vote */
		doc.setNbVote(doc.getNbVote()+1);
		
		/* Mise à jour du doc dans la base de données */
		IDocumentDAO docDAO = getDocDAOForDocument(doc);
		docDAO.update(doc);
	}
	
	@Override
	public Collection<User> getDocumentsVoteByUserIdDocId(Long userId, Long docId,DocumentType docType){
		IDocumentDAO docDAO = docDaoMap.get(docType);
		return docDAO.getDocumentsVoteByUserIdDocId(userId,docId);
	}
	
	@Override
	public Collection<Document> getDocumentsByTagName(Collection<Tag> listTags){
		/*Collection<Tag> test = new ArrayList<Tag>();
		Tag tag1 = new Tag();
		tag1.setName("azer");
		test.add(tag1);*/
		return imageDAO.getDocumentsByTagsName(listTags);
	}
	/* fin modifié */
	
	

}
