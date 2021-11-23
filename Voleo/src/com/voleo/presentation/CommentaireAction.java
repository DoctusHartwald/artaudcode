package com.voleo.presentation;

import java.util.Collection;

import org.apache.commons.validator.routines.LongValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.user.User;
import com.voleo.service.ICommentaireService;
import com.voleo.service.IDocumentService;
import com.voleo.service.IUserService;

@Component
public class CommentaireAction extends AbstractAction{

	@Autowired
	private IDocumentService documentService;
	
	@Autowired 
	private IUserService userService;
	
	@Autowired
	private ICommentaireService commentaireService;
	
	private Long docId;
	private Collection<Commentaire> commentaire;
	private Commentaire newCommentaire;
	private String docType;
	private int commentaireCount;
	private Collection<Document> nlastText; 
	private Long commentaireId;
	private Commentaire selectedCommentaire;
	private Long notificationCommentaireId;
	private int nouvelleNote;
	
	public String ajoutNoteCommentaire(){
		if (commentaireId != null) {
			Long userId = getUserIdInSession();
			Commentaire com = commentaireService.getCommentaireById(commentaireId);
			if(userService.aVoteCommentaire(userId, com))
				return SUCCESS;
			commentaireService.voteCommentaire(userId, com);
			commentaireService.ajoutNoteCommentaire(com, nouvelleNote);
			userService.ajoutNouvelleNoteCommentaire(nouvelleNote, com.getUser().getId());
		}
		return SUCCESS;
	}
	
	public String getCommentaireByDocumentId(){
			Long userId = getUserIdInSession();
			commentaire = commentaireService.getAllCommentsByDocumentId(docId);
			for(Commentaire i:commentaire){
				i.setUserWhoHasVoted(commentaireService.getDocumentsVoteByUserIdComId(userId, i.getId()));
				if(i.getUserWhoHasVoted().isEmpty())
					System.out.println("vide");
				for(User j: i.getUserWhoHasVoted())
					System.out.println("u:"+j.getLogin());
			}
			commentaireCount = commentaireService.getCountCommentBydocId(docId);
			nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);

		return SUCCESS;
	}

	public String getSelectedCommentaireByDocumentId(){
		selectedCommentaire = commentaireService.getCommentaireById(commentaireId);
		return SUCCESS;
	}
	
	public String addCommentaireForm() {
		try {
			newCommentaire = Commentaire.class.newInstance();
		} catch (Exception e) {
			// Should not occurs
		}
		return SUCCESS;
	}
	
	public String addCommentaire(){
		Long userId = getUserIdInSession();
		DocumentType type = DocumentType.valueOf(docType);
		commentaireService.addCommentaire(newCommentaire,type,docId,userId);
		commentaireService.addNotificationCommentaire(newCommentaire, type, docId, userId);
		commentaireService.voteCommentaire(userId, newCommentaire);
		return SUCCESS;
	}
	
	
	public String deleteNotificationCommentaire(){
		if (notificationCommentaireId != null) {
		commentaireService.deleteNotificationCommentaire(notificationCommentaireId);
		 return SUCCESS;
		}
		else{
			return "failed";
		}
	}
	
/*Getters & Setters*/
	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}


	public Collection<Commentaire> getCommentaires() {
		return commentaire;
	}


	public void setCommentaires(Collection<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}

	public Collection<Commentaire> getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Collection<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}

	public Commentaire getNewCommentaire() {
		return newCommentaire;
	}

	public void setNewCommentaire(Commentaire newCommentaire) {
		this.newCommentaire = newCommentaire;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public int getCommentaireCount() {
		return commentaireCount;
	}

	public void setCommentaireCount(int commentaireCount) {
		this.commentaireCount = commentaireCount;
	}

	public Collection<Document> getNlastText() {
		return nlastText;
	}

	public void setNlastText(Collection<Document> nlastText) {
		this.nlastText = nlastText;
	}

	public Long getCommentaireId() {
		return commentaireId;
	}

	public void setCommentaireId(Long commentaireId) {
		this.commentaireId = commentaireId;
	}
	
	public int getNouvelleNote() {
		return nouvelleNote;
	}
	
	public void setNouvelleNote(int nouvelleNote) {
		this.nouvelleNote = nouvelleNote;
	}
	
	public Commentaire getSelectedCommentaire() {
		return selectedCommentaire;
	}

	public void setSelectedCommentaire(Commentaire selectedCommentaire) {
		this.selectedCommentaire = selectedCommentaire;
	}




}
