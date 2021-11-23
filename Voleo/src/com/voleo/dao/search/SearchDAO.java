package com.voleo.dao.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;

import org.apache.lucene.search.Query;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.builtin.DateBridge;
import org.hibernate.search.bridge.builtin.LongBridge;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Component;

import com.voleo.dto.search.SearchRequest;
import com.voleo.dto.search.SearchResult;
import com.voleo.entity.bridge.TagCollectionStringBridge;
import com.voleo.entity.bridge.UserStringBridge;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;

@Component
public class SearchDAO implements ISearchDAO {
	private JpaTemplate jpaTemplate;
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.jpaTemplate = new JpaTemplate(emf);
	}

	public Collection<SearchResult> searchDocuments(SearchRequest request) {
		LuceneQueryBuilder queryBuilder = new LuceneQueryBuilder();
		Query query = queryBuilder.createQuery(request);
		return executeQuery(query);
	}

	@SuppressWarnings("unchecked")
	private Collection<SearchResult> executeQuery(final Query query) {
		return (Collection<SearchResult>)jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em)
					throws PersistenceException {
				FullTextEntityManager ftEm = Search.createFullTextEntityManager(em);
				FullTextQuery ftQuery = ftEm.createFullTextQuery(query, Document.class);
				ftQuery.setProjection(FullTextQuery.DOCUMENT, FullTextQuery.SCORE);
				return documentsToSearchResult(ftQuery.getResultList());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private Collection<SearchResult> documentsToSearchResult(
			List<Object[]> docs) {
		List<SearchResult> results = new ArrayList<SearchResult>(docs.size());
		DateBridge minuteBridge = new DateBridge(Resolution.MINUTE);
		LongBridge longBridge = new LongBridge();
		TagCollectionStringBridge tagBridge = new TagCollectionStringBridge();
		UserStringBridge userBridge = new UserStringBridge();
		
		for (Object[] docArray : docs) {
			SearchResult result = new SearchResult();
			org.apache.lucene.document.Document doc = 
				(org.apache.lucene.document.Document)docArray[0];
			result.setId(
					(Long)extractFieldFromDoc(doc, Document.ID_FIELD, longBridge));
			result.setName(
					(String)extractFieldFromDoc(doc, Document.NAME_FIELD, null));
			result.setCreateDate(
					(Date)extractFieldFromDoc(doc, Document.CREATE_DATE_FIELD, minuteBridge));
			result.setUpdateDate(
					(Date)extractFieldFromDoc(doc, Document.UPDATE_DATE_FIELD, minuteBridge));
			result.setTags(
					(List<String>)extractFieldFromDoc(doc, Document.TAGS_FIELD, tagBridge));
			result.setUserPseudo(
					(String)extractFieldFromDoc(doc, Document.USER_FIELD, userBridge));
			result.setScore((Float)docArray[1] * 100.0f);
			
			String className = (String)extractFieldFromDoc(doc, Document.CLASS_FIELD, null);
			if (className != null) {
				result.setDocumentType(DocumentType.getByClassName(className));
			}
				
			results.add(result);
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	private Object extractFieldFromDoc(org.apache.lucene.document.Document doc, 
			String fieldName, TwoWayStringBridge bridge) {
			String s = doc.get(fieldName);
			if (s == null) {
				return null;
			}
		
			if (bridge == null) {
				return s;
			} else {
				return bridge.stringToObject(s);
			}
	}
}
