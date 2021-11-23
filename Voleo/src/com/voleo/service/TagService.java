package com.voleo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.document.IDocumentDAO;
import com.voleo.dao.document.ITagDAO;
import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Tag;
import com.voleo.entity.document.Text;


@Component
@Transactional
public class TagService implements ITagService{

	@Autowired
	private ITagDAO tagDAO;
	
	
	
	/* modifié */
	@Override
	public List<String> getAllTags() {
		Collection<Tag> allTags = tagDAO.getAllTag();
		List<String> allTagsList = new ArrayList();
		for(Tag i : allTags)
			if(i.getName() != null && !i.getName().isEmpty())
				allTagsList.add(i.getName());
		return allTagsList;
	}
	
	@Override
	public Collection<Tag> getMostUseTags(){
		Collection<Tag> allTags = tagDAO.getMostUseTags();
		return allTags;
		/*List allTagsList = new ArrayList();
		for(String i : allTags)
			if(i != null && !i.isEmpty())
				allTagsList.add(i);
		return allTagsList;*/
	}
	
	@Override
	public Collection<Tag> getTagsByDocuments(Collection<Document> listDocs){
		return tagDAO.getTagsByDocuments(listDocs);
	}
	/* fin modifié */
	
	
	@Override
	public Tag getTagById(Long id) {
		return tagDAO.getById(id);
	}


	@Override
	public void addTag(Tag newTag) {
		tagDAO.add(newTag);
	}

	@Override
	public void updateTag(Tag updateTag){
		tagDAO.update(updateTag);
	}
	



	@Override
	public Collection<Tag> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Collection<Tag> getDocumentTag(Long docId) {
			return tagDAO.getDocumentTag(docId);
	}


	@Override
	public Collection<TagCount> getTagNamesAndDocumentsCount() {
		return tagDAO.getAllNameAndDocumentsCount();
	}
	
	@Override
	public Collection<TagCount> getTagNamesAndDocumentsCountByDocuments(Collection<Document> docs){
		return tagDAO.getFilteredNameAndDocumentsCount(docs);
	}
}
