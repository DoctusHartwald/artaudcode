package com.voleo.dao.search;

import java.util.Collection;

import com.voleo.dto.search.SearchRequest;
import com.voleo.dto.search.SearchResult;
import com.voleo.entity.document.Document;

public interface ISearchDAO {
	public Collection<SearchResult> searchDocuments(SearchRequest request);
}
