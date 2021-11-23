package com.voleo.service;

import java.util.Collection;

import com.voleo.dto.admin.DateCountDocument;
import com.voleo.dto.admin.DateCountImage;
import com.voleo.dto.admin.DateCountVideo;
import com.voleo.entity.backUp.BackUpModification;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.Modification;
import com.voleo.entity.document.Tag;
import com.voleo.entity.user.User;

public interface IDocumentService {
 
	public void addDocument(Document doc, Long userId, String[] tags);
	public void updateDocument(Document doc, Long userId);
	public void removeDocument(DocumentType docType, Long docId);
	public Document getDocument(DocumentType docType, Long docId);
	public Collection<Document> getAllMyDocument(DocumentType docType, Long userId);
	public Collection<Document> getLastNMyDocument(DocumentType docType, Long userId, int amount);
	public int getMyDocumentCount(DocumentType docType, Long userId);
//	public Collection<Text> getAllTextOfOthers();
	public Collection<Document> getAllValidated(DocumentType docType, Long userId);
	public Collection<Modification> getAllPendingModification(DocumentType docType, Long userId);
	public Collection<Document> getAllDocument(DocumentType docType,Long userId);
	public Collection<Document> getNLastDocument(DocumentType docType, int amount);
	public byte[] getImageContent(Long docId);
	public byte[] getVideoContent(Long docId);
	public void modificationDecision(DocumentType docType, Long modId, boolean approve);
	public int getCountPendingByUserId(DocumentType docType,Long userId);
	public int getCountAllByUser(Long userId);
	public int getCountAllPendingByUserId(Long userId);
	public Modification getModification(DocumentType docType, Long modId);
	public BackUpModification getBackUpDocument(DocumentType docType,Long docId);
	/* modifié */
	public void ajoutNoteArticle(Document doc, int nouvelleNote);
	public Collection<User> getDocumentsVoteByUserIdDocId(Long userId, Long docId, DocumentType docType);
	public Collection<Document> getDocumentsByTagName(Collection<Tag> listTags);
	/* fin modifié */
	public void addBackUpModification(DocumentType docType,Document doc);
	public byte[] getDocContent(Long docId);
	
	//Ajout pour Graphique ;
	public Collection<DateCountDocument> getDateCountDocument();
	public Collection<DateCountImage> getDateCountImage();
	public Collection<DateCountVideo> getDateCountVideo();
	void voteArticle(Long userId, Document doc);
	
	//Fin part graph 
}
