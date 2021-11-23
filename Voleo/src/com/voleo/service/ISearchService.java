package com.voleo.service;

import java.util.Collection;

import com.voleo.dto.search.SearchRequest;
import com.voleo.dto.search.SearchResult;

public interface ISearchService {
	public Collection<SearchResult> search(SearchRequest request);
}
