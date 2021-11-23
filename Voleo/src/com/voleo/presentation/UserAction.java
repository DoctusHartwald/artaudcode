package com.voleo.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voleo.service.IContactService;
import com.voleo.service.IDocumentService;
import com.voleo.service.IForumService;
import com.voleo.service.IHistoriqueService;
import com.voleo.service.IUserService;

@Component
public class UserAction extends AbstractAction {
	
	@Autowired
	private IDocumentService documentService;
	
	@Autowired 
	private IContactService contactService;
	
	@Autowired 
	private IHistoriqueService historiqueService;
	
	@Autowired 
	private IForumService forumService;
	
	@Autowired
	private IUserService userService;
	
	private int docCount;
	private int docPendingCount;
	private int contactCount;
	private int historiqueCount;
	private int newForumCount;
	private int adminCount;
	private int userCount;
	private int forumCount;
		
	public String getEspacePerso(){
		Long userId = getUserIdInSession();
		docCount = documentService.getCountAllByUser(userId);
		docPendingCount = documentService.getCountAllPendingByUserId(userId);
		contactCount = contactService.getCountContactByUser(userId);
		historiqueCount = historiqueService.getCountHistoriqueByUser(userId);
		forumCount = forumService.getCountDiscussionByForumId();
		newForumCount = forumService.getCountNewDiscussionByForumId();
		userCount = userService.getCountAllUser();
		adminCount = userService.getCountAllAdmin();
		return SUCCESS;
	}

	public int getDocCount() {
		return docCount;
	}

	public void setDocCount(int docCount) {
		this.docCount = docCount;
	}

	public int getDocPendingCount() {
		return docPendingCount;
	}

	public void setDocPendingCount(int docPendingCount) {
		this.docPendingCount = docPendingCount;
	}

	public int getContactCount() {
		return contactCount;
	}

	public void setContactCount(int contactCount) {
		this.contactCount = contactCount;
	}

	public int getHistoriqueCount() {
		return historiqueCount;
	}

	public void setHistoriqueCount(int historiqueCount) {
		this.historiqueCount = historiqueCount;
	}

	public int getNewForumCount() {
		return newForumCount;
	}

	public void setNewForumCount(int newForumCount) {
		this.newForumCount = newForumCount;
	}

	public int getAdminCount() {
		return adminCount;
	}

	public void setAdminCount(int adminCount) {
		this.adminCount = adminCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getForumCount() {
		return forumCount;
	}

	public void setForumCount(int forumCount) {
		this.forumCount = forumCount;
	}
	
}
