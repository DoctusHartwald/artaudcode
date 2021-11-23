package com.voleo.dao.document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Tag;

@Component
public class TagDAO extends AbstractDAO<Tag> implements ITagDAO {
	public TagDAO() {
		super(Tag.class);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Tag getByName(String name){
		List<Tag> results = 
			jpaTemplate.find("from Tag t where t.name = ?1", name);
		if (results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Tag> getDocumentTag(Long docId){
		//return jpaTemplate.find("from Document d inner join fetch d.tags where d.id = ?1",docId);
		return jpaTemplate.find("select t from Tag t left outer join t.documents d where d.id=?1",docId);
	}

	/* modifié */
	/*public Collection<String> getAllTag(){
		return jpaTemplate.find("select distinct t.name from Tag t order by t.name");
	}*/
	public Collection<Tag> getAllTag(){
		return jpaTemplate.find("select t from Tag t group by t.name order by t.name");
	}
	/*Fin mofif*/
	
	public Collection<Tag> getMostUseTags(){
		return jpaTemplate.find("select t from Tag t group by t.name order by count(*) desc");
	}
	
	public Collection<Tag> getTagsByDocuments(Collection<Document> listDocs){
		if(listDocs.isEmpty())
			return null;
		String request = "select t from Tag t left outer join t.documents d where";
		String requestTemp = " t.id in (select t.id from Tag t left outer join t.documents d where d.id = ";		
		for(Document i : listDocs)
			request += requestTemp + i.getId()+ ") or";
		request = request.substring(0, request.length()- 3);
		request +=" group by t.name order by t.name";
		return jpaTemplate.find(request);
	}
	/* fin modifié */

	public Collection<TagCount> getAllNameAndDocumentsCount() {
		List<TagCount> tags = new ArrayList<TagCount>();
		
		List<Object[]> results =
			jpaTemplate.find("select t.name, size(t.documents) from Tag t group by t.name order by t.name");
		
		for (Object[] result : results) {
			TagCount tag = new TagCount();
			tag.setName((String)result[0]);
			tag.setDocumentsCount(((Number)result[1]).intValue());
			tags.add(tag);
		}
		return tags;
	}
	
	public Collection<TagCount> getFilteredNameAndDocumentsCount(Collection<Document> listDocs){
		if(listDocs.isEmpty())
			return null;
		List<TagCount> tags = new ArrayList<TagCount>();
		String request = "select t.name, size(t.documents) from Tag t left outer join t.documents d where";
		String requestTemp = " t.id in (select t.id from Tag t left outer join t.documents d where d.id = ";		
		for(Document i : listDocs)
			request += requestTemp + i.getId()+ ") or";
		request = request.substring(0, request.length()- 3);
		request +=" group by t.name order by t.name";
		List<Object[]> results = jpaTemplate.find(request);
		for (Object[] result : results) {
			TagCount tag = new TagCount();
			tag.setName((String)result[0]);
			tag.setDocumentsCount(((Number)result[1]).intValue());
			tags.add(tag);
		}
		return tags;
	}
}
