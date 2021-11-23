package com.voleo.service;

import java.util.Collection;
import java.util.List;

import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Tag;

public interface ITagService {

	public Tag getTagById(Long id);
	public Collection<Tag> getByName(String name);
	
	public Collection<Tag> getDocumentTag(Long docId);
	public void addTag(Tag newTag);
	public void updateTag(Tag updateTag);

	public Collection<TagCount> getTagNamesAndDocumentsCount();
	
	public Collection<Tag> getMostUseTags();
	public List<String> getAllTags();
	public Collection<Tag> getTagsByDocuments(Collection<Document> listDocs);
	public Collection<TagCount> getTagNamesAndDocumentsCountByDocuments(Collection<Document> docs);
	
}
