package com.voleo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voleo.dao.search.ISearchDAO;
import com.voleo.dto.search.SearchRequest;
import com.voleo.dto.search.SearchResult;

@Component
public class SearchService implements ISearchService {

	@Autowired
	private ISearchDAO searchDAO;
	

	
	@Override
	public Collection<SearchResult> search(SearchRequest request) {
		return searchDAO.searchDocuments(request);
	}

}
