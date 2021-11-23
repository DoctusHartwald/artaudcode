package com.voleo.presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voleo.dto.admin.DateCountCommentaire;
import com.voleo.dto.admin.DateCountDocument;
import com.voleo.dto.admin.DateCountForum;
import com.voleo.dto.admin.DateCountImage;
import com.voleo.dto.admin.DateCountVideo;
import com.voleo.dto.admin.PseudoInfo;
import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.news.News;
import com.voleo.entity.user.User;
import com.voleo.entity.user.UserType;
import com.voleo.service.IAdminService;
import com.voleo.service.ICommentaireService;
import com.voleo.service.IDocumentService;
import com.voleo.service.IForumService;
import com.voleo.service.ITagService;
import com.voleo.service.IUserService;

@Component
public class AdminAction extends AbstractAction{

	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IUserService userService;
	
	private Collection<User> allUser;
	private Collection<User> allAdmin;
	private User userSearched;
	private User userSelected;
	private Long newsId;
	private News news;
	private Collection<News> allNews;
	private Long userId;
	private String userChange;
	private Collection<User >searchedUser;
	private String flag="search"; 
	
	
	
////******************************
	/////////A COPIER ///////////////////////
	//Control Panel 
	//Partie Control Panel 
	
	
	@Autowired
	private ICommentaireService commentaireService;


	
	
	@Autowired
	private IDocumentService documentService;
	private List pseudoList;
	private List allPseudo;  			//Recupere tous les Pseudo de la Bdd 
	private User userSearch ; 
	private Collection<NotificationCommentaire> allNotificationCommentaire;
	
	
	
	
	
	
	//A Copier pour la date Gaphique 
	@Autowired
	private ITagService tagService;
	
	
	@Autowired
	private IForumService forumService;
	
	
	private Collection<DateCountCommentaire> dateCommentaires ;
	private Collection<DateCountDocument> dateDocuments;
	private Collection<DateCountImage> dateImage;
	private Collection<DateCountVideo> dateVideo;
	private Collection<DateCountForum> dateForum;
	private Collection<PseudoInfo> pseudoInfo ; 
	
	private int numberCommentaire ; 
	private String  dateCommentaire ;
	private List adminCommentaire ;  //Count Commentaire , doc , image 
	private List adminCountDocument ; 
	private List adminCountImage; 
	private List adminCountVideo; 
	private List adminCountForum; //Count message Forum 
	private List pseudoInfoResult;  //Surveillance des users
	
	//User CSV for Excel 
	private List allUserInformation;
	private List resultsCVS;
	
    public static SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
    private Collection<TagCount> tags;
    
	
	public String userSearchForm(){
		userSearch = new User();
		return SUCCESS ; 
	}
	
	public String flag(){
		flag="search";
		return SUCCESS;
	}
	
	public String userSearch(){
		System.out.println("PSEUDO:" +userSearch.getPseudo());
		String pseudo = userSearch.getPseudo();
		searchedUser = adminService.getUserByPseudo(pseudo);
		flag="display";
		for(User u:searchedUser){
			u.setDocCount(adminService.documentCountByUSerId(u.getId()));
			u.setCommentaireCount(adminService.commentaireCountByUserId(u.getId()));
			u.setNoteGenerale(userService.getUserSMarkById(u.getId()));
		}
		
		pseudoList (); // return pseudo List		
		return SUCCESS;
	}
	
	public String control(){
		Long userId = getUserIdInSession();
		allUser = adminService.getAllTypeUser();

		///Ajout des Pseudo
		pseudoList ();
		return SUCCESS;
	}
	public String pseudoList(){
		pseudoList = new ArrayList(); 
		for(User u : allUser){
			if(u!=null){
				String pseudo =" ' " +u.getPseudo()+" ' ";
				pseudoList.add(pseudo);
			}
		}
		return SUCCESS;
	}
	///Date Count Commentaire 
	public String dateCountCommentaire() throws ParseException{
		dateCommentaires = adminService.getdateCountCommentaire();
		adminCommentaire = new ArrayList(); 
		int i =0;
		for(DateCountCommentaire result : dateCommentaires ){
			if(result!=null){
		        Date d=stringToDate(result.getDateFormat());
				String resultatFormat = "["+d.getTime()+","+result.getCountcommentaire()+"]";
				adminCommentaire.add(resultatFormat);
			}
		}
		
		documentCount();  //Appel document count
		imageCount();	  //Appel Image count
		videoCount();	  //Appel Video Count
		//pr div Tag 
		tags = tagService.getTagNamesAndDocumentsCount();

		return SUCCESS;
	}
	
    public static Date stringToDate(String sDate) throws ParseException {
    	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        return formatter.parse(sDate);
    }
    
    ///Document Graph
    //return nb of doc according to the date in Bdd 
    public String documentCount() throws ParseException{
    	dateDocuments = documentService.getDateCountDocument();
    	adminCountDocument =new ArrayList<DateCountDocument>();
    	for (DateCountDocument result : dateDocuments) {
    		if(result!=null){
    			Date dateDocument = stringToDate(result.getDateFormat());
    			String resultatFormat = "["+dateDocument.getTime()+","+result.getCountNumberDocument()+"]";
        		adminCountDocument.add(resultatFormat);
    		}
		}
    	return SUCCESS;
    }
    
    ///Image Graph
    public String imageCount() throws ParseException{
    	dateImage =documentService.getDateCountImage();
    	adminCountImage = new ArrayList<DateCountDocument>();
    	for (DateCountImage results : dateImage) {
    		if(results!=null){
    			Date dateImageFormat =stringToDate(results.getDateFormat());
    			String resultatFormat = "["+dateImageFormat.getTime()+","+results.getCountNumberDocument()+"]";
        		adminCountImage.add(resultatFormat);
    		}
		}
    	return SUCCESS ; 
    }
    
    ///Video Graph
    public String videoCount() throws ParseException{
    	dateVideo =documentService.getDateCountVideo();
    	adminCountVideo = new ArrayList<DateCountVideo>();
    	for (DateCountVideo results : dateVideo) {
    		if(results!=null){
    			Date dateVideoFormat =stringToDate(results.getDateFormat());
    			String resultatFormat = "["+dateVideoFormat.getTime()+","+results.getCountNumberDocument()+"]";
        		adminCountVideo.add(resultatFormat);
    		}
		}
    	
    	return SUCCESS;
    }
    
    ///Forum Graph
    public String forumCount() throws ParseException {
    	dateForum = forumService.getDateCountForum();
    	adminCountForum = new ArrayList<DateCountForum>();
    	for (DateCountForum result : dateForum) {
			Date dateForumFormat =stringToDate(result.getDateFormat());
			String resultFormat = "["+dateForumFormat.getTime()+","+result.getCountNumberDocument()+"]";
			adminCountForum.add(resultFormat);
			
		}
    	return SUCCESS ; 
    }
    
    ///CVS Function
	public String exportFormatComma(){
		allUser = adminService.getAllTypeUser();
		allUserInformation = new ArrayList<String>();
		
		String headOfDocument = "Pseudo,Login,UserType,"+"Notation générale des Docs (/5)"+","+"Nombre de Docs"+","+"Nombre de ses Docs Notés"+","+"Nombres de Commentaires"+"\n";
		
		allUserInformation.add(headOfDocument);
		for (User result : allUser) {
			String formatExportComma =result.getPseudo()+","+result.getLogin()+","+result.getNotation()+","+result.getDocCount()+","+result.getHitArticle()+","+result.getNbComment()+"\n";
			allUserInformation.add(formatExportComma);
			//System.out.println("Resultat All Informations  *******");
			//System.out.println(""+result.getPseudo()+","+result.getLogin()+","+result.getNotation()+","+result.getDocCount()+","+result.getHitArticle()+","+result.getNbComment());
		}

		resultsCVS = new ArrayList<String>();
	      try {
			System.setOut(new PrintStream(new FileOutputStream("C:\\exportCvsWithComma.txt")));
			// Redirect the output to the file:	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    for (int i=0 ;i<allUserInformation.size();i++) {
	    	System.out.println(allUserInformation.get(i));
		}
		return SUCCESS;
	}
	
	//Recuperer Commentaire et Document 
	
	public String surveillance(){
		Long userId = getUserIdInSession();
		 pseudoInfo = commentaireService.surveillance(userId);
		 pseudoInfoResult = new ArrayList<PseudoInfo>();
		 for (PseudoInfo result : pseudoInfo) {
			String surveillance =" "+ result.getCommentaire()+" : "+result.getName();
			pseudoInfoResult.add(surveillance);
			System.out.println("RESULTAT $$$$$$$$$$$");
			System.out.println(pseudoInfoResult);
		}
		return SUCCESS;
	}
	//Format date 
	
	

	
	
	
	public String getAllNotification(){
		
		allNotificationCommentaire = commentaireService.getAllNotificationCommentaire();
		
		return SUCCESS;
	}

	public List getAllPseudo() {
		return allPseudo;
	}

	public void setAllPseudo(List allPseudo) {
		this.allPseudo = allPseudo;
	}
	
	public List getPseudoList() {
		return pseudoList;
	}

	public void setPseudoList(List pseudoList) {
		this.pseudoList = pseudoList;
	}

	public User getUserSearch() {
		return userSearch;
	}

	public void setUserSearch(User userSearch) {
		this.userSearch = userSearch;
	}

	public String adminListUser(){
		allUser = adminService.getAllTypeUser();
		return SUCCESS;
	}

	////////*************************/////
	//Fin Copie Autocomplete Admin 
	
	
	
	
	
	
	
	
	
	
	public String adminAddUpdateUserForm(){
		userSelected = new User();
		allUser = adminService.getAllTypeUser();
		//allAdmin = adminService.getAllTypeAdmin();
		return SUCCESS;
	}

	public String searchUser(){
		System.out.println("USerId:  "+userId);
		System.out.println("USer Id 2: "+userSelected.getId());

		userSearched = adminService.getUserById(userSelected.getId());
		return SUCCESS;
	}
	
	public String deleteUser(){
		adminService.removeUser(userId);
		return listUser();
	}
	
	public String updateTypUserToAdmin(){
		adminService.updateTypeUserToAdmin(userId);
		return adminAddUpdateUserForm();
	}
	
	
	public String updateNewsForm(){
		
		news = adminService.getNewsById(newsId);
		return SUCCESS;
	}
	public String updateNews(){
		
		adminService.updateNews(news);
		return listAllNews();
	}
	
	public String addNewsForm(){
		news = new News();
		return SUCCESS;
	}
	
	public String addNews(){
		adminService.addNews(news);
		return listAllNews();
	}
	
	public String removeNews(){
		adminService.removeNews(newsId);
		return listAllNews();
	}
	
	public String listAllNews(){
		allNews = adminService.getAllNews();
		return SUCCESS;
	}
	
	public String listUser(){
		allUser = adminService.getAllTypeUser();
		for(User u:allUser){
			u.setDocCount(adminService.documentCountByUSerId(u.getId()));
			u.setCommentaireCount(adminService.commentaireCountByUserId(u.getId()));
			u.setNoteGenerale(userService.getUserSMarkById(u.getId()));
		}
		return SUCCESS;
	}
	
	public String changeUserType(){
		User user = userService.getUserById(userId);
		System.out.println("ONCHANGE: "+user.getUserType());
		String change = user.getUserType().toString();
		
		if(change.equals("USER")){
			user.setUserType(UserType.ADMIN);
		}
		if(change.equals("ADMIN")){
			user.setUserType(UserType.USER);			
		}
		userService.update(user);
		return listUser();
	}
	
	
	
	/*Getters & Setters*/
	
	public Collection<User> getAllUser() {
		return allUser;
	}

	public void setAllUser(Collection<User> allUser) {
		this.allUser = allUser;
	}
	
	public User getUserSearched() {
		return userSearched;
	}

	public void setUserSearched(User userSearched) {
		this.userSearched = userSearched;
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}


	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Collection<News> getAllNews() {
		return allNews;
	}

	public void setAllNews(Collection<News> allNews) {
		this.allNews = allNews;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Collection<User> getAllAdmin() {
		return allAdmin;
	}

	public void setAllAdmin(Collection<User> allAdmin) {
		this.allAdmin = allAdmin;
	}

	public String getUserChange() {
		return userChange;
	}

	public void setUserChange(String userChange) {
		this.userChange = userChange;
	}

	public Collection<User> getSearchedUser() {
		return searchedUser;
	}

	public void setSearchedUser(Collection<User> searchedUser) {
		this.searchedUser = searchedUser;
	}

	/*graph*/
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Collection<NotificationCommentaire> getAllNotificationCommentaire() {
		return allNotificationCommentaire;
	}

	public void setAllNotificationCommentaire(
			Collection<NotificationCommentaire> allNotificationCommentaire) {
		this.allNotificationCommentaire = allNotificationCommentaire;
	}

	public Collection<DateCountCommentaire> getDateCommentaires() {
		return dateCommentaires;
	}

	public void setDateCommentaires(
			Collection<DateCountCommentaire> dateCommentaires) {
		this.dateCommentaires = dateCommentaires;
	}

	public Collection<DateCountDocument> getDateDocuments() {
		return dateDocuments;
	}

	public void setDateDocuments(Collection<DateCountDocument> dateDocuments) {
		this.dateDocuments = dateDocuments;
	}

	public Collection<DateCountImage> getDateImage() {
		return dateImage;
	}

	public void setDateImage(Collection<DateCountImage> dateImage) {
		this.dateImage = dateImage;
	}

	public Collection<DateCountVideo> getDateVideo() {
		return dateVideo;
	}

	public void setDateVideo(Collection<DateCountVideo> dateVideo) {
		this.dateVideo = dateVideo;
	}

	public Collection<DateCountForum> getDateForum() {
		return dateForum;
	}

	public void setDateForum(Collection<DateCountForum> dateForum) {
		this.dateForum = dateForum;
	}

	public Collection<PseudoInfo> getPseudoInfo() {
		return pseudoInfo;
	}

	public void setPseudoInfo(Collection<PseudoInfo> pseudoInfo) {
		this.pseudoInfo = pseudoInfo;
	}

	public int getNumberCommentaire() {
		return numberCommentaire;
	}

	public void setNumberCommentaire(int numberCommentaire) {
		this.numberCommentaire = numberCommentaire;
	}

	public String getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(String dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public List getAdminCountDocument() {
		return adminCountDocument;
	}

	public void setAdminCountDocument(List adminCountDocument) {
		this.adminCountDocument = adminCountDocument;
	}

	public List getAdminCountImage() {
		return adminCountImage;
	}

	public void setAdminCountImage(List adminCountImage) {
		this.adminCountImage = adminCountImage;
	}

	public List getAdminCountVideo() {
		return adminCountVideo;
	}

	public void setAdminCountVideo(List adminCountVideo) {
		this.adminCountVideo = adminCountVideo;
	}

	public List getAdminCountForum() {
		return adminCountForum;
	}

	public void setAdminCountForum(List adminCountForum) {
		this.adminCountForum = adminCountForum;
	}

	public List getPseudoInfoResult() {
		return pseudoInfoResult;
	}

	public void setPseudoInfoResult(List pseudoInfoResult) {
		this.pseudoInfoResult = pseudoInfoResult;
	}

	public List getAllUserInformation() {
		return allUserInformation;
	}

	public void setAllUserInformation(List allUserInformation) {
		this.allUserInformation = allUserInformation;
	}

	public List getResultsCVS() {
		return resultsCVS;
	}

	public void setResultsCVS(List resultsCVS) {
		this.resultsCVS = resultsCVS;
	}

	public static SimpleDateFormat getFormatter() {
		return formatter;
	}

	public static void setFormatter(SimpleDateFormat formatter) {
		AdminAction.formatter = formatter;
	}

	public Collection<TagCount> getTags() {
		return tags;
	}

	public void setTags(Collection<TagCount> tags) {
		this.tags = tags;
	}

	public List getAdminCommentaire() {
		return adminCommentaire;
	}

	public void setAdminCommentaire(List adminCommentaire) {
		this.adminCommentaire = adminCommentaire;
	}


}
