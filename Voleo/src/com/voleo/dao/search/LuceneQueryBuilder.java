package com.voleo.dao.search;

import java.util.Calendar;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.bridge.builtin.DateBridge;

import com.voleo.dto.search.SearchRequest;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.RawFile;
import com.voleo.entity.document.Text;

public class LuceneQueryBuilder {
	private static final String EPOCH;
	
	static {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 01, 01);
		
		EPOCH = new DateBridge(Resolution.MINUTE).objectToString(cal.getTime());
	}
	//Query = Classe Abstraite
	public Query createQuery(SearchRequest request) {
		
		//BooleanQuery = Classe Concrète
		BooleanQuery booleanQuery = new BooleanQuery();
		
		if (request.getKeywords() != null) {
			Query query = createOrJoinedQuery(
					request.getKeywords(),
					Document.NAME_FIELD,
					Document.TAGS_FIELD,
					Text.TEXT_FIELD,
					RawFile.CONTENT_FIELD);
			booleanQuery.add(query, Occur.MUST);
		}

		if (request.getFirstDate() != null) {
			DateBridge minuteBridge = new DateBridge(Resolution.MINUTE);

			String field = request.getSearchDateType().getFieldName();
			String firstDateString = 
				minuteBridge.objectToString(request.getFirstDate());
			Term fromTerm = new Term(field, firstDateString);
			
			Query rangeQuery = null;
			switch (request.getSearchDateMode()) {
			case AFTER:
				rangeQuery = new RangeQuery(new Term(field, EPOCH), fromTerm, false);
				booleanQuery.add(rangeQuery, Occur.MUST_NOT);
				break;
			case BEFORE:
				rangeQuery = new RangeQuery(new Term(field, EPOCH), fromTerm, false);
				booleanQuery.add(rangeQuery, Occur.MUST);
				break;
			case RANGE:
				if (request.getSearchDateType() != null 
						&& request.getFirstDate().compareTo(request.getSecondDate()) <= 0) {
					
					String secondDateString = 
						minuteBridge.objectToString(request.getSecondDate());
					Term toTerm = new Term(field, secondDateString);

					rangeQuery = new RangeQuery(fromTerm, toTerm, true);
					booleanQuery.add(rangeQuery, Occur.MUST);
				}
			}

		}
		
		if (request.getDocumentType() != null) {
			String entityClassName = 
				request.getDocumentType().getEntityClass().getName();
			Term term = new Term(Document.CLASS_FIELD, entityClassName);
			Query termQuery = new TermQuery(term);
			booleanQuery.add(termQuery, Occur.MUST);
		}
		
		if (request.getUserPseudo() != null && !"".equals(request.getUserPseudo().trim())) {
			Term term = 
				new Term(Document.USER_FIELD, request.getUserPseudo());
			Query termQuery = new TermQuery(term);
			booleanQuery.add(termQuery, Occur.MUST);
		}
		
		return booleanQuery;
	}
	//String... = String[] avec un nbre d'arguments variable
	private Query createOrJoinedQuery(String keyword, String... fields) {
		BooleanQuery joinQuery = new BooleanQuery();
		for (int i = 0; i < fields.length; i++) {
			//Sur Lucene Term = association d'un champ et d'un mot clé.
			Term term = new Term(fields[i], keyword);//champs, valeur des champs
			TermQuery query = new TermQuery(term);
			joinQuery.add(query, Occur.SHOULD);//Jointure ici: SHOULD = OU , Must = ET
		}
		return joinQuery;
	}
}
