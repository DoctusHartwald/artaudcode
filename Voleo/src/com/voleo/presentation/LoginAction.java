package com.voleo.presentation;

import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.opensymphony.xwork2.ActionContext;
import com.voleo.dao.forum.INotificationWallToWallDAO;
import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.forum.Forum;
import com.voleo.entity.forum.NotificationForum;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.news.News;
import com.voleo.entity.user.Contact;
import com.voleo.entity.user.User;
import com.voleo.service.IAdminService;
import com.voleo.service.ICommentaireService;
import com.voleo.service.IContactService;
import com.voleo.service.IDocumentService;
import com.voleo.service.IForumService;
import com.voleo.service.IHistoriqueService;
import com.voleo.service.ITagService;
import com.voleo.service.IUserService;

@Component
public class LoginAction extends AbstractAction {

	private static final Long NULL = null;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDocumentService documentService;
	
	@Autowired 
	private IAdminService adminService;
	
	@Autowired 
	private IContactService contactService;
	
	@Autowired 
	private IHistoriqueService historiqueService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired 
	private IForumService forumService;
	
	@Autowired 
	private ICommentaireService commentaireService;
	
	@Autowired 
	private INotificationWallToWallDAO notificationWallToWallDAO;
	
	private String login;
	private String password;
	private boolean invalidLogin;
	private Collection<Document> nlastText; 
	private Collection<Document> nLastImage;
	private Collection<Document> nLastVideo;
	private Collection<Document> nLastDocument;
	private Collection<Contact> contacts;
	private Collection<News> allNews;
	private Collection<TagCount> tagsCount;
	private String userType;
	private int docCount;
	private int docPendingCount;
	private int contactCount;
	private int userCount;
	private int adminCount;
	private int historiqueCount;
	private int textCount;
	private int imageCount;
	private int videoCount;
	private int rawfileCount;
	private int forumCount;
	private int newForumCount;
	private Collection<Document> myNlastText;
	private Collection<NotificationForum> nLastNotificationForum;
	private String flag = "plus";
	private Collection<NotificationWallToWall> nLastNotificationWallToWall;
	private Collection<NotificationCommentaire> nLastNotificationCommentaire;
	private Collection<Forum> listDiscussionSansReponse;
	public String login() {
		User loggedInUser = userService.login(login, password);
		/*userType = loggedInUser.getUserType().toString();
		System.out.println("User Type : "+userType);*/
		if (loggedInUser == null) {
			// affichage login faux
			invalidLogin = true;
			return "failed";
		} else {
			//sauvegarde de l'id utilisateur dans la session http
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("userId", loggedInUser.getId());
			invalidLogin = false;

			Long userId = getUserIdInSession();
			docCount = documentService.getCountAllByUser(userId);
			docPendingCount = documentService.getCountAllPendingByUserId(userId);
			contactCount = contactService.getCountContactByUser(userId);
			historiqueCount = historiqueService.getCountHistoriqueByUser(userId);
			myNlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
			
			
			return SUCCESS;
		}
	}
	
	public String execute() {
		/*invalidLogin = false;
		login = null;
		password = null;*/
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
		textCount = nlastText.size();
		nLastImage = documentService.getNLastDocument(DocumentType.IMAGE, 4);
		imageCount = nLastImage.size();
		nLastDocument = documentService.getNLastDocument(DocumentType.RAWFILE, 4);
		rawfileCount = nLastDocument.size();
		nLastVideo = documentService.getNLastDocument(DocumentType.VIDEO, 4);
		videoCount = nLastVideo.size();
		allNews = adminService.getAllNews();
		userCount = userService.getCountAllUser();
		adminCount = userService.getCountAllAdmin();
		tagsCount = tagService.getTagNamesAndDocumentsCount();
		forumCount = forumService.getCountDiscussionByForumId();
		newForumCount = forumService.getCountNewDiscussionByForumId();
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
		nLastNotificationForum = forumService.getNLastNotificationForum(2);
		nLastNotificationWallToWall= notificationWallToWallDAO.getNLastNotificationWallToWall(4);	
		nLastNotificationCommentaire = commentaireService.getNLastNotificationCommentaire(4);
  		listDiscussionSansReponse = forumService.DiscussionSansReponse();
  		
		Long userId = getUserIdInSession();
		if(userId !=NULL){
			docPendingCount = documentService.getCountAllPendingByUserId(userId);
			userType = userService.getUserById(userId).getUserType().toString();
		}
		else{
			userType = null;
		}
		try {
			contacts = ContactAction.class.newInstance().getContacts();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}


	
	public boolean getInvalidLogin() {
		return invalidLogin;
	}
	
	public String disconnect(){
		invalidLogin = false;
		login = null;
		password = null;
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("userId");
		session.remove("tagsSelected");
		return SUCCESS;
	}
	
	public void resetInvalidLogin(){
		invalidLogin=false;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	


	public Collection<Document> getNlastText() {
		return nlastText;
	}

	public void setNlastText(Collection<Document> nlastText) {
		this.nlastText = nlastText;
	}

	public Collection<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Collection<News> getAllNews() {
		return allNews;
	}

	public void setAllNews(Collection<News> allNews) {
		this.allNews = allNews;
	}

	public int getDocCount() {
		return docCount;
	}

	public void setDocCount(int docCount) {
		this.docCount = docCount;
	}

	public void setInvalidLogin(boolean invalidLogin) {
		this.invalidLogin = invalidLogin;
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

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getAdminCount() {
		return adminCount;
	}

	public void setAdminCount(int adminCount) {
		this.adminCount = adminCount;
	}

	public int getHistoriqueCount() {
		return historiqueCount;
	}

	public void setHistoriqueCount(int historiqueCount) {
		this.historiqueCount = historiqueCount;
	}

	public Collection<TagCount> getTagsCount() {
		return tagsCount;
	}

	public void setTagsCount(Collection<TagCount> tagsCount) {
		this.tagsCount = tagsCount;
	}

	public Collection<Document> getNLastImage() {
		return nLastImage;
	}

	public void setNLastImage(Collection<Document> lastImage) {
		nLastImage = lastImage;
	}

	public Collection<Document> getNLastVideo() {
		return nLastVideo;
	}

	public void setNLastVideo(Collection<Document> lastVideo) {
		nLastVideo = lastVideo;
	}

	public Collection<Document> getNLastDocument() {
		return nLastDocument;
	}

	public void setNLastDocument(Collection<Document> lastDocument) {
		nLastDocument = lastDocument;
	}

	public int getTextCount() {
		return textCount;
	}

	public void setTextCount(int textCount) {
		this.textCount = textCount;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public int getRawfileCount() {
		return rawfileCount;
	}

	public void setRawfileCount(int rawfileCount) {
		this.rawfileCount = rawfileCount;
	}

	public int getForumCount() {
		return forumCount;
	}

	public void setForumCount(int forumCount) {
		this.forumCount = forumCount;
	}

	public int getNewForumCount() {
		return newForumCount;
	}

	public void setNewForumCount(int newForumCount) {
		this.newForumCount = newForumCount;
	}

	public Collection<NotificationForum> getNLastNotificationForum() {
		return nLastNotificationForum;
	}

	public void setNLastNotificationForum(
			Collection<NotificationForum> lastNotificationForum) {
		nLastNotificationForum = lastNotificationForum;
	}



	public Collection<NotificationWallToWall> getNLastNotificationWallToWall() {
		return nLastNotificationWallToWall;
	}

	public void setNLastNotificationWallToWall(
			Collection<NotificationWallToWall> lastNotificationWallToWall) {
		nLastNotificationWallToWall = lastNotificationWallToWall;
	}

	public Collection<Document> getMyNlastText() {
		return myNlastText;
	}

	public void setMyNlastText(Collection<Document> myNlastText) {
		this.myNlastText = myNlastText;
	}

	public Collection<NotificationCommentaire> getNLastNotificationCommentaire() {
		return nLastNotificationCommentaire;
	}

	public void setNLastNotificationCommentaire(
			Collection<NotificationCommentaire> lastNotificationCommentaire) {
		nLastNotificationCommentaire = lastNotificationCommentaire;
	}

	public Collection<Forum> getListDiscussionSansReponse() {
		return listDiscussionSansReponse;
	}

	public void setListDiscussionSansReponse(
			Collection<Forum> listDiscussionSansReponse) {
		this.listDiscussionSansReponse = listDiscussionSansReponse;
	}


}
