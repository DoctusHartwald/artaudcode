package com.voleo.dao.document;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.dto.admin.DateCountDocument;
import com.voleo.dto.admin.DateCountImage;
import com.voleo.dto.admin.DateCountVideo;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Tag;
import com.voleo.entity.user.User;

public interface IDocumentDAO<T extends Document> extends IDAO<T> {
	public Collection<T> getAllByUser(User user);
	
	public Collection<T> getAllByUserId(Long userId);

	public Collection<Document> getNLastByUser(Long userId, int amount);
	
	public int getCountByUser(Long userId);
	
	public Collection<T> getAllPending(Long userId);
	
	public Collection<T> getAllValidated(Long userId);

	public Collection<Document> getNLastDocument(final int amount);
	
	public Collection<T> getAllOtherDocument(Long userId);

	public int getCountAllByUser(Long userId);

	/* modifié */
	public Collection<T> getDocumentsVoteByUserIdDocId(Long userId, Long docId);

	public Collection<Document> getDocumentsByTagsName(Collection<Tag> listTags);
	/* fin modifié */
	//Partie Grpahique pour les Documents 
	public  Collection<DateCountDocument>  getDateCountDocument();
	public Collection<DateCountImage> getDateCountImage();
	public Collection<DateCountVideo> getDateCountVideo();
	//Fin Graphe

	public Collection<User> getDocumentsVoteByUserIdDocIdTest(Long userId, Long docId);
}
