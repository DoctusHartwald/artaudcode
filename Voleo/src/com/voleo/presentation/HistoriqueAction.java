package com.voleo.presentation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voleo.entity.backUp.BackUpModification;
import com.voleo.entity.backUp.TextBackUpModification;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.Historique;
import com.voleo.entity.document.Text;
import com.voleo.entity.user.User;
import com.voleo.service.IBackUpModificationService;
import com.voleo.service.IDocumentService;
import com.voleo.service.IHistoriqueService;

@Component
public class HistoriqueAction extends AbstractAction{
	
	@Autowired
	private IHistoriqueService historiqueService;
	
	@Autowired 
	private IDocumentService documentService;
	
	@Autowired 
	private IBackUpModificationService backUpModificationService;
	
	private Collection<Historique> allhistoriqueByUser;
	private Document document;
	private User user;
	private String docType;
	private Long historiqueId;
	private Long backUpId;
	private BackUpModification getBackUpDocument;
	private Collection<Document> nlastText; 

	
	public String getHistoriqueByUser(){
		
		Long userId = getUserIdInSession();
		allhistoriqueByUser = historiqueService.getAllByUserId(userId);
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);

		return SUCCESS;
	}

	public String getDocumentBackUpModification(){
		DocumentType type = DocumentType.valueOf(docType);
		if(docType.equals("TEXT")){
			getBackUpDocument = (TextBackUpModification)getBackUpDocument;
		}
		
		getBackUpDocument = documentService.getBackUpDocument(type, historiqueId);
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);

		return getHistoriqueByUser();
	}
	
	public String approuvePreviousDocumentVersion(){
		if (historiqueId != null) {
			Long userId = getUserIdInSession();
			System.out.println("BackUpId : "+backUpId);
			BackUpModification backUpModification = backUpModificationService.getBackUpModification(backUpId);
			Long docId = backUpModification.getTargetDocument().getId();
			
			document = documentService.getDocument(DocumentType.TEXT, docId);
			
			document.setName(backUpModification.getName());
			((Text)document).setText(((TextBackUpModification)backUpModification).getText());
		
			
			documentService.updateDocument(document, userId);
			nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);

		}
		return SUCCESS;
	}
	
	
	public String supprimerHistorique(){
		if (historiqueId != null) {
			nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
			backUpModificationService.supprimerBackModificationById(historiqueId);
		}
		return getHistoriqueByUser();
	}
	
	
	
	/*Getters & Setters*/
	public Collection<Historique> getAllhistoriqueByUser() {
		return allhistoriqueByUser;
	}

	public void setAllhistoriqueByUser(Collection<Historique> allhistoriqueByUser) {
		this.allhistoriqueByUser = allhistoriqueByUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	public BackUpModification getGetBackUpDocument() {
		return getBackUpDocument;
	}

	public void setGetBackUpDocument(BackUpModification getBackUpDocument) {
		this.getBackUpDocument = getBackUpDocument;
	}

	public Long getBackUpId() {
		return backUpId;
	}

	public void setBackUpId(Long backUpId) {
		this.backUpId = backUpId;
	}

	public Long getHistoriqueId() {
		return historiqueId;
	}

	public void setHistoriqueId(Long historiqueId) {
		this.historiqueId = historiqueId;
	}

	public Collection<Document> getNlastText() {
		return nlastText;
	}

	public void setNlastText(Collection<Document> nlastText) {
		this.nlastText = nlastText;
	}




}
