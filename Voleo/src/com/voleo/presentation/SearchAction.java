package com.voleo.presentation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voleo.dto.document.TagCount;
import com.voleo.dto.search.SearchRequest;
import com.voleo.dto.search.SearchResult;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.user.User;
import com.voleo.service.IDocumentService;
import com.voleo.service.ISearchService;
import com.voleo.service.ITagService;
import com.voleo.service.IUserService;

@Component
public class SearchAction extends AbstractAction {

	@Autowired
	private ISearchService searchService;
	
	@Autowired 
	private IUserService userService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private IDocumentService documentService;
	
	private SearchRequest request;
	private Collection<SearchResult> results;
	private Collection<User> allUser;
	private Collection<TagCount> tagsCount;
	private Collection<Document> nlastText; 

	
	public String searchForm() {
		request = new SearchRequest();
		allUser = userService.getAllUser();
		tagsCount = tagService.getTagNamesAndDocumentsCount();
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
		return SUCCESS;
	}

	public String search() {
		results = searchService.search(request);
		tagsCount = tagService.getTagNamesAndDocumentsCount();
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
		return SUCCESS;
	}


	public SearchRequest getRequest() {
		return request;
	}

	public void setRequest(SearchRequest request) {
		this.request = request;
	}

	public Collection<SearchResult> getResults() {
		return results;
	}

	public Collection<User> getAllUser() {
		return allUser;
	}

	public void setAllUser(Collection<User> allUser) {
		this.allUser = allUser;
	}

	public Collection<TagCount> getTagsCount() {
		return tagsCount;
	}

	public void setTagsCount(Collection<TagCount> tagsCount) {
		this.tagsCount = tagsCount;
	}

	public Collection<Document> getNlastText() {
		return nlastText;
	}

	public void setNlastText(Collection<Document> nlastText) {
		this.nlastText = nlastText;
	}
}
