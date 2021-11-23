package com.voleo.dao.document;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Tag;

public interface ITagDAO extends IDAO<Tag> {

	public Tag getByName(String name);
	public Collection<Tag> getDocumentTag(Long docId);
	public Collection<TagCount> getAllNameAndDocumentsCount();
	
	
	
	/* modifié */
	public Collection<Tag> getAllTag();
	
	public Collection<Tag> getMostUseTags();
	
	public Collection<Tag> getTagsByDocuments(Collection<Document> listDocs);
	public Collection<TagCount> getFilteredNameAndDocumentsCount(Collection<Document> listDocs);
	/*fin modifié*/
}
