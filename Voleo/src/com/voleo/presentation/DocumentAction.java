package com.voleo.presentation;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.Image;
import com.voleo.entity.document.Modification;
import com.voleo.entity.document.RawFile;
import com.voleo.entity.document.Tag;
import com.voleo.entity.document.Text;
import com.voleo.entity.document.TextModification;
import com.voleo.entity.document.Video;
import com.voleo.entity.user.User;
import com.voleo.service.IBackUpModificationService;
import com.voleo.service.ICommentaireService;
import com.voleo.service.IDocumentService;
import com.voleo.service.IHistoriqueService;
import com.voleo.service.ITagService;
import com.voleo.service.IUserService;

@Component
public class DocumentAction extends AbstractAction{

	@Autowired
	private IDocumentService documentService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired 
	private IUserService userService;
	
	@Autowired
	private ICommentaireService commentaireService;
	
	@Autowired
	private IHistoriqueService historiqueService;
	
	@Autowired
	private IBackUpModificationService backUpService;
	
	
	private Collection<TagCount> tagsCount;

	private File file;
	private InputStream imageStream;
	private InputStream docStream;
	private InputStream videoStream;
	private OutputStream outStream;
	
	private String docType;
	private Collection<Tag> tags;

	private String newDocumentName;
	private String newDocumentText;
	private Collection<Tag> documenttag;
	private Text textForm;
	private Tag tagsForm;
	
	private Document doc;
	private Long docId;
	private Long modId;
	private Collection<Document> allValidated;
	private Collection<Modification> allPending;
	private Collection<Document> allText; 
	private Collection<Document> allImage;
	private Collection<Document> allVideo;
	private Collection<Document> allRawFile;
	private Collection<Document> lastNMyDocuments;
	private Document searchDocument; 
	private List allTags;
	private int textCount;
	private int imageCount;
	private int videoCount;
	private int rawfileCount;
	private int pendingTextCount;
	private int pendingImageCount;
	private int pendingVideoCount;
	private int pendingRawFileCount;
	private int historiqueCount;
	private String newTagName;
	private String name;
	private Long id;
	private int commentaireCount;
	private Modification modificationHistorique;
	private User userHistorique;
	private User userOrigine;
	private Document docHistorique;
	private Collection<Document> documentList; 
	private Collection<Document> nlastText; 
	
	/* Nouveaux attributs */
	private int nouvelleNote;
	private String tagsSelected;
	private Collection<Document> allDocToShowAfterFilter;
	private Collection<Tag> allTagToShowAfterFilter;
	private Collection<String> allTagsSelected;
	/* Fin nouveaux attributs */

	private Collection<Document> nLastImage;

	private Collection<Document> nLastDocument;

	private Collection<Document> nLastVideo;

	private int textCountLatest;

	private int imageCountLatest;

	private int rawfileCountLatest;

	private int videoCountLatest;
	
	
	/* methodes modifiés */
	public String lastArticles(){
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
		textCountLatest = nlastText.size();
		nLastImage = documentService.getNLastDocument(DocumentType.IMAGE, 4);
		imageCountLatest = nLastImage.size();
		nLastDocument = documentService.getNLastDocument(DocumentType.RAWFILE, 4);
		rawfileCountLatest = nLastDocument.size();
		nLastVideo = documentService.getNLastDocument(DocumentType.VIDEO, 4);
		videoCountLatest = nLastVideo.size();
		return SUCCESS;
	}
	
	public String listAvailableTag() {
		/* Récupère la session */
		Map<String, Object> session = ActionContext.getContext().getSession();
		/*String pass;
		do {
			pass = (String) session.get("pass");
			if (pass == null)
				break;
			try {
				Thread.sleep((long) 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (pass.equals("no"));
		session.put("pass", "no");*/
		String tagsSelected = (String) session.get("tagsSelected");
		/* s'il n'y a pas de tags alors on affiche les tags les plus utilisés */
		if (tagsSelected == null || tagsSelected.isEmpty()) {
			tagsCount = tagService.getTagNamesAndDocumentsCount();
			//allTagToShowAfterFilter = tagService.getMostUseTags();
		}
		/* sinon */
		else {
			/* on prend la liste de tous les tags selectionnées */
			Collection<Tag> listTags = new ArrayList<Tag>();
			String[] allSelectedTags = tagsSelected.split(";");
			for (String aTag : allSelectedTags) {
				Tag newTag = new Tag();
				newTag.setName(aTag);
				listTags.add(newTag);
			}
			/* on récupère les documents ayant les tags selectionnées */
			allDocToShowAfterFilter = documentService.getDocumentsByTagName(listTags);
			Long userId = getUserIdInSession();
			if(userId!=null)
				for(Document i: allDocToShowAfterFilter)
					i.setUserWhoHasVoted(documentService.getDocumentsVoteByUserIdDocId(userId, i.getId(),DocumentType.valueOf(i.getType())));
			else
				for(Document i: allDocToShowAfterFilter)
					i.setUserWhoHasVoted(null);
			/* on récupère l'ensemble des tags présents dans ces docs */
			//allTagToShowAfterFilter = tagService.getTagsByDocuments(allDocToShowAfterFilter);
			
			tagsCount = tagService.getTagNamesAndDocumentsCountByDocuments(allDocToShowAfterFilter);
			/* on enlève des tags présents ceux qui sont déjà sélectionnées */
			/*Collection<Tag> allTagToShowTmp = new ArrayList<Tag>();
			for (Tag i : allTagToShowAfterFilter) {
				Boolean itExist = false;
				for (String j : allSelectedTags)
					if (j.equals(i.getName())) {
						itExist = true;
						break;
					}
				if (!itExist)
					allTagToShowTmp.add(i);
			}
			allTagToShowAfterFilter = allTagToShowTmp;*/
			Collection<TagCount> tagsCountTmp = new ArrayList<TagCount>();
			for (TagCount i : tagsCount) {
				Boolean itExist = false;
				for (String j : allSelectedTags)
					if (j.equals(i.getName())) {
						itExist = true;
						break;
					}
				if (!itExist)
					tagsCountTmp.add(i);
			}
			tagsCount = tagsCountTmp;
		}
		//session.put("pass", "yes");
		return SUCCESS;
	}

	public String addSelectedTag() {
		/* Récupère la session */
		Map<String, Object> session = ActionContext.getContext().getSession();
		/*String pass;
		do {
			pass = (String) session.get("pass");
			if (pass == null)
				break;
			try {
				Thread.sleep((long) 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (pass.equals("no"));
		session.put("pass", "no");*/
		String tagsSelectedTmp = (String) session.get("tagsSelected");
		/* si vide ou n'existe pas alors on crée un nouveau */
		if (tagsSelectedTmp == null || tagsSelectedTmp.isEmpty())
			session.put("tagsSelected", tagsSelected + ";");
		else
			/* on rajoute le nouveau tag selectionné aux précédents */
			session.put("tagsSelected", tagsSelectedTmp + tagsSelected + ";");
		tagsSelected = (String) session.get("tagsSelected");
		Collection<Tag> listTags = new ArrayList<Tag>();
		String[] allSelectedTags = tagsSelected.split(";");
		for (String aTag : allSelectedTags) {
			Tag newTag = new Tag();
			newTag.setName(aTag);
			listTags.add(newTag);
		}
		/* on récupère les documents ayant les tags selectionnées */
		allDocToShowAfterFilter = documentService.getDocumentsByTagName(listTags);
		Long userId = getUserIdInSession();
		if(userId!=null)
			for(Document i: allDocToShowAfterFilter){
				i.setUserWhoHasVoted(documentService.getDocumentsVoteByUserIdDocId(userId, i.getId(),DocumentType.valueOf(i.getType())));
			}
		else
			for(Document i: allDocToShowAfterFilter)
				i.setUserWhoHasVoted(null);
		//session.put("pass", "yes");
		return SUCCESS;
	}

	public String removeSelectedTag() {
		/* Récupère la session */
		Map<String, Object> session = ActionContext.getContext().getSession();
		/*String pass;
		do {
			pass = (String) session.get("pass");
			if (pass == null)
				break;
			try {
				Thread.sleep((long) 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (pass.equals("no"));
		session.put("pass", "no");*/
		String tagsSelectedTmp = (String) session.get("tagsSelected");
		/*
		 * si on souhaite supprimé un tag alors qu'il n'existe pas... problème
		 * mais renvoie success
		 */
		if (tagsSelectedTmp == null || tagsSelectedTmp.isEmpty()){
			tagsCount = tagService.getTagNamesAndDocumentsCount();
			return SUCCESS;
		}
		/* récupéré la liste des tags déjà selectionné */
		String[] allSelectedTags = tagsSelectedTmp.split(";");
		String newTagsSelected = new String("");
		/*
		 * créer la nouvelle liste de tags selectionné sans le tag
		 * sélectionner par l'utilisateur qui est a supprimer
		 */
		for (String aTag : allSelectedTags)
			if (!tagsSelected.equals(aTag))
				newTagsSelected += aTag + ";";
			else
				System.out.println(tagsSelected);
		System.out.println(newTagsSelected);
		session.put("tagsSelected", newTagsSelected);
		tagsSelected = (String) session.get("tagsSelected");
		Collection<Tag> listTags = new ArrayList<Tag>();
		allSelectedTags = tagsSelected.split(";");
		for (String aTag : allSelectedTags) {
			Tag newTag = new Tag();
			newTag.setName(aTag);
			listTags.add(newTag);
		}
		/* on récupère les documents ayant les tags selectionnées */
		allDocToShowAfterFilter = documentService.getDocumentsByTagName(listTags);
		Long userId = getUserIdInSession();
		if(userId!=null)
			for(Document i: allDocToShowAfterFilter){
				i.setUserWhoHasVoted(documentService.getDocumentsVoteByUserIdDocId(userId, i.getId(),DocumentType.valueOf(i.getType())));
			}
		else
			for(Document i: allDocToShowAfterFilter)
				i.setUserWhoHasVoted(null);
		//session.put("pass", "yes");
		return SUCCESS;
	}

	public String listSelectedTag() {
		/* Récupère la session */
		Map<String, Object> session = ActionContext.getContext().getSession();
		/*String pass;
		do {
			pass = (String) session.get("pass");
			if (pass == null)
				break;
			try {
				Thread.sleep((long) 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (pass.equals("no"));
		session.put("pass", "no");*/
		String tagsSelectedTmp = (String) session.get("tagsSelected");
		/* aucun tag selectionné */
		if (tagsSelectedTmp == null || tagsSelectedTmp.isEmpty())
			allTagsSelected = null;
		/* Créer la liste des tags sélectionnés */
		else {
			allTagsSelected = new ArrayList<String>();
			String[] allSelectedTags = tagsSelectedTmp.split(";");
			for (String aTag : allSelectedTags)
				allTagsSelected.add(aTag);
		}
		//session.put("pass", "yes");
		return SUCCESS;
	}

	public String listDocumentFiltered() {
		/* Récupère la session */
		Map<String, Object> session = ActionContext.getContext().getSession();
		/*String pass;
		do {
			pass = (String) session.get("pass");
			if (pass == null)
				break;
			try {
				Thread.sleep((long) 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (pass.equals("no"));
		session.put("pass", "no");*/
		String tagsSelected = (String) session.get("tagsSelected");
		/* récupérer l'ensemble des tags selectionné */
		Collection<Tag> listTags = new ArrayList<Tag>();
		String[] allSelectedTags = tagsSelected.split(";");
		for (String aTag : allSelectedTags) {
			Tag newTag = new Tag();
			newTag.setName(aTag);
			listTags.add(newTag);
		}
		/* récupéré les docs ayant l'ensemble des tags selectionnés */
		allDocToShowAfterFilter = documentService.getDocumentsByTagName(listTags);
		Long userId = getUserIdInSession();
		if(userId!=null)
			for(Document i: allDocToShowAfterFilter){
				i.setUserWhoHasVoted(documentService.getDocumentsVoteByUserIdDocId(userId, i.getId(),DocumentType.valueOf(docType)));
			}
		else
			for(Document i: allDocToShowAfterFilter)
				i.setUserWhoHasVoted(null);
		//session.put("pass", "yes");
		return SUCCESS;
	}
	
	public String ajoutNoteArticle(){
		if (docId != null) {
			Long userId = getUserIdInSession();
			DocumentType type = DocumentType.valueOf(docType);
			doc = documentService.getDocument(type, docId);
			
			if(userService.aVoteArticle(userId, doc))
				return listDocuments();
			documentService.voteArticle(userId, doc);
			documentService.ajoutNoteArticle(doc, nouvelleNote);
			userService.ajoutNouvelleNoteArticle(nouvelleNote, doc.getUser().getId());
		}
		return listDocuments();
	}
	
	
	public String listDocuments() {
		Long userId = getUserIdInSession();
		DocumentType type = DocumentType.valueOf(docType);
		documentList = documentService.getAllMyDocument(type, userId);
		//modification
		for(Document i: documentList){
			i.setUserWhoHasVoted(documentService.getDocumentsVoteByUserIdDocId(userId, i.getId(),type));
			
			commentaireCount = commentaireService.getCountCommentBydocId(i.getId());
			i.setCountCommentaire(commentaireCount);
		}
		return execute();
	}
	
	public String getSearchedDocument(){
		Long userId = getUserIdInSession();
		DocumentType type = DocumentType.valueOf(docType);
		searchDocument = documentService.getDocument(type, docId);
		commentaireCount = commentaireService.getCountCommentBydocId(docId);
		searchDocument.setCountCommentaire(commentaireCount);
		//modification
		searchDocument.setUserWhoHasVoted(documentService.getDocumentsVoteByUserIdDocId(userId, searchDocument.getId(),type));

		return SUCCESS;
	}

	public String addDocumentForm() {
		DocumentType type = DocumentType.valueOf(docType);
		try {
			doc = type.getEntityClass().newInstance();
			allTags = tagService.getAllTags();
			tagsCount = tagService.getTagNamesAndDocumentsCount();
		} catch (Exception e) {
			// Should not occurs
		}
		return docType.toLowerCase();
	}
	
	public String addDocument(){
		Long userId = getUserIdInSession();
		String[] allNewTag;
		if (newTagName.equals("")) {
			allNewTag = new String[1];
			allNewTag[0] = "sans tag";
		} else
			allNewTag = newTagName.split(";");
		for (String aTag : allNewTag) {
			Tag newTag = new Tag();
			doc.getTags().add(newTag);
			newTag.setName(aTag);
		}
		if(docType.equals("IMAGE")){
			Image image = (Image)doc;
			
			if (file != null) {
				int fileSize = (int)file.length();
				byte[] imageContent = new byte[fileSize];
				try {
					FileInputStream input = new FileInputStream(file);
					int reads = input.read(imageContent);
					if (reads == fileSize) {
						image.setImage(imageContent);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			doc = (Document)image;
		}

		
		if(docType.equals("RAWFILE")){
			RawFile rawFile = (RawFile)doc;
			
			if (file != null) {
				int fileSize = (int)file.length();
				byte[] rawFileContent = new byte[fileSize];
				try {
					FileInputStream input = new FileInputStream(file);
					int reads = input.read(rawFileContent);
					if (reads == fileSize) {
						rawFile.setContent(rawFileContent);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			doc = (Document)rawFile;
			}

		if(docType.equals("VIDEO")){
			Video video = (Video)doc;
			
			if (file != null) {
				int fileSize = (int)file.length();
				byte[] videoContent = new byte[fileSize];
				try {
					FileInputStream input = new FileInputStream(file);
					int reads = input.read(videoContent);
					if (reads == fileSize) {
						video.setVideo(videoContent);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			doc = (Document)video;
		}
		
		/*if(docType.equals("TEXT")){
			documentService.addBackUpModification(DocumentType.TEXT, doc);
		}*/
		documentService.addDocument(doc, userId, allNewTag);
		documentService.voteArticle(userId, doc);
		userService.ajoutArticle(userId);

		return listDocuments();
	}
	/* fin methodes modifiés */	
	
	public String removeDocument(){		
		DocumentType type = DocumentType.valueOf(docType);
		if (docId != null) {
			documentService.removeDocument(type, docId);
		}
		return listDocuments();
	}
	
	public String updateDocumentForm() {
		DocumentType type = DocumentType.valueOf(docType);
		if (docId != null) {
			doc = documentService.getDocument(type, docId);
			tags = tagService.getDocumentTag(docId);
		}
		switch (type) {
		case TEXT:
			Modification modification = new TextModification();
			modification.setTargetDocument(doc);
			
			Long userId = getUserIdInSession();
			User user = userService.getUserById(userId);
			modification.setUser(user);
			
			doc.setModification(modification);
			break;
		}
		return docType.toLowerCase();
	}
	
	public String updateDocument(){		
		if (docId != null) {
			Long userId = getUserIdInSession();

			//System.out.println("ID Tag : "+id);
//			Set<Tag> tagsHashSet = new HashSet<Tag>();
//			Tag updateTag = new Tag();
//			updateTag.setName(name);
//			tagsHashSet.add(updateTag);
//			doc.setTags(tagsHashSet);
			
			
//			Tag updateTag = new Tag();
//			doc.getTags().add(updateTag);
//			updateTag.setName(newTagName);
			
			/*documentService.updateDocument(doc, userId);
			String[] allNewTag;
			if (newTagName.equals("")) {
				allNewTag = new String[1];
				allNewTag[0] = "sans tag";
			} else
				allNewTag = newTagName.split(";");
			for (String aTag : allNewTag) {
				Tag newTag = new Tag();
				//tagsUpdated.add(newTag);
				newTag.setName(aTag);
				doc.getTags().add(newTag);
			}*/
			documentService.updateDocument(doc, userId);
		}
		return listDocuments();
	}


	
	public String displayImage() {
		//Long id = LongValidator.getInstance().validate(imageId);
		byte[] imageContent = documentService.getImageContent(docId);	
		imageStream = new ByteArrayInputStream(imageContent);
		
		return SUCCESS;
	}
	
	public String approve() {
		Long userId = getUserIdInSession();
		DocumentType type = DocumentType.TEXT;//DocumentType.valueOf(docType);
		
		modificationHistorique = documentService.getModification(type, modId);
		userOrigine = userService.getUserById(userId); // récupère User orig
		userHistorique = modificationHistorique.getUser(); // récupère User de la modification
		docId = modificationHistorique.getTargetDocument().getId(); 
		docHistorique = documentService.getDocument(type, docId);//récupère doc modifié
		
		//historiqueService.addHistorique(userOrigine,userHistorique, docHistorique);
		
		documentService.modificationDecision(type, modId, true);
		return listPendingModifications();
	}
	
	public String reject() {
		DocumentType type = DocumentType.TEXT;//DocumentType.valueOf(docType);
		documentService.modificationDecision(type, modId, false);
		return listPendingModifications();
	}
	
	public String listPendingModifications() {
		Long userId = getUserIdInSession();
		DocumentType type = DocumentType.TEXT;//DocumentType.valueOf(docType);
		allPending = documentService.getAllPendingModification(type, userId);
		
		return SUCCESS;
	}
	
	public String listPendingTypes(){
		Long userId = getUserIdInSession();
		pendingTextCount = documentService.getCountPendingByUserId(DocumentType.TEXT, userId);
		pendingImageCount = documentService.getCountPendingByUserId(DocumentType.IMAGE, userId);
		pendingVideoCount = documentService.getCountPendingByUserId(DocumentType.VIDEO, userId);
		pendingRawFileCount = documentService.getCountPendingByUserId(DocumentType.RAWFILE, userId);
		historiqueCount = historiqueService.getCountHistoriqueByUser(userId);
		
		return SUCCESS;
	}
	
	public String listAllDocuments() {
		Long userId = getUserIdInSession();
		allText = documentService.getAllDocument(DocumentType.TEXT,userId);
		allImage = documentService.getAllDocument(DocumentType.IMAGE,userId);
		allVideo = documentService.getAllDocument(DocumentType.VIDEO,userId);
		allRawFile = documentService.getAllDocument(DocumentType.RAWFILE,userId);
		
		return Action.SUCCESS;
	}

	public String listTypes() {
		Long userId = getUserIdInSession();
		textCount = documentService.getMyDocumentCount(DocumentType.TEXT, userId);
		System.out.println(textCount);
		imageCount = documentService.getMyDocumentCount(DocumentType.IMAGE, userId);
		System.out.println(imageCount);
		videoCount = documentService.getMyDocumentCount(DocumentType.VIDEO, userId);
		System.out.println(videoCount);
		rawfileCount = documentService.getMyDocumentCount(DocumentType.RAWFILE, userId);
		System.out.println(rawfileCount);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);
		nLastImage = documentService.getNLastDocument(DocumentType.IMAGE, 4);
		nLastDocument = documentService.getNLastDocument(DocumentType.RAWFILE, 4);
		nLastVideo = documentService.getNLastDocument(DocumentType.VIDEO, 4);
		return execute();*/
		return SUCCESS;
	}

	public String getLastNMyDocument(){
		
		Long userId = getUserIdInSession();
		DocumentType type = DocumentType.valueOf(docType);
		lastNMyDocuments = documentService.getLastNMyDocument(type, userId, 2);
		return Action.SUCCESS;
	}
	
	public String addCommentaire(){
		Long userId = getUserIdInSession();
		DocumentType type = DocumentType.valueOf(docType);
		
		return Action.SUCCESS;
	}

	
	public String execute(){
		tagsCount = tagService.getTagNamesAndDocumentsCount();
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);

		return SUCCESS;
	}
	
	public InputStream getImageStream() {
		return imageStream;
	}
	
	public String downloadFile() {
		byte[] imageContent = documentService.getImageContent(docId);	
		imageStream = new ByteArrayInputStream(imageContent);
		
		return SUCCESS;
	}
	
	public String downloadDoc() {
		byte[] docContent = documentService.getDocContent(docId);	
		docStream = new ByteArrayInputStream(docContent);
		
		return "successPdf";
	}
	
	public InputStream getVideoStream() {
		return videoStream;
	}
	
	public String downloadVideo(){
		byte[] videoContent = documentService.getVideoContent(docId);
		if(videoContent!=null)
			videoStream = new ByteArrayInputStream(videoContent);
		else
			videoStream= null;
		return SUCCESS;
	}
	/*
	public String addDocument() {
		DocumentType type = DocumentType.valueOf(docType);
		Tag newTag = new Tag();
		Text text = new Text();
		Image image = new Image();
		
		switch(type) {
		case TEXT:
			break;
		case IMAGE:
			break;
		case VIDEO:
			break;
		case RAWFILE:
			break;
		}
		
		
		if (file != null) {
			int fileSize = (int)file.length();
			byte[] imageContent = new byte[fileSize];
			try {
				FileInputStream input = new FileInputStream(file);
				int reads = input.read(imageContent);
				if (reads == fileSize) {
					image.setImage(imageContent);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		documentService.addImage(image);
		
		Long userId = getUserIdInSession();
		//user = userService.getUserById(userId);	
		//newContact.setUser(user);
		Set<Tag> tags = text.getTags();
		tags.add(newTag);
		//text.getTags().add(newTag);
		newTag.setName(newTagName);
		text.setName(newDocumentName);
		text.setText(newDocumentText);
		
		textService.addText(text,userId);
		//documentService.addDocument(text);
		return execute();
	}
	

	public String displayImage() {
		Long id = LongValidator.getInstance().validate(imageId);
		byte[] imageContent = documentService.getImageContent(id);	
		imageStream = new ByteArrayInputStream(imageContent);
		
		return SUCCESS;
	}
*/
	
	
	
	
	
	
	
	
	
	/*---------------Setters et Getters--------------------*/
	
	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
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

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public String getNewTagName() {
		return newTagName;
	}
	public void setNewTagName(String newTagName) {
		this.newTagName = newTagName;
	}

	public Collection<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(Collection<Document> documentList) {
		this.documentList = documentList;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public void setAllValidated(Collection<Document> allValidated) {
		this.allValidated = allValidated;
	}

	public Collection<Document> getAllValidated() {
		return allValidated;
	}

	public Collection<Modification> getAllPending() {
		return allPending;
	}

	public Collection<Document> getAllText() {
		return allText;
	}

	public void setAllText(Collection<Document> allText) {
		this.allText = allText;
	}

	public Collection<Document> getAllImage() {
		return allImage;
	}

	public void setAllImage(Collection<Document> allImage) {
		this.allImage = allImage;
	}

	public Collection<Document> getAllVideo() {
		return allVideo;
	}

	public void setAllVideo(Collection<Document> allVideo) {
		this.allVideo = allVideo;
	}

	public Collection<Document> getAllRawFile() {
		return allRawFile;
	}

	public void setAllRawFile(Collection<Document> allRawFile) {
		this.allRawFile = allRawFile;
	}

	public Collection<Document> getLastNMyDocuments() {
		return lastNMyDocuments;
	}

	public void setLastNMyDocuments(Collection<Document> lastNMyDocuments) {
		this.lastNMyDocuments = lastNMyDocuments;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}

	public Document getSearchDocument() {
		return searchDocument;
	}

	public void setSearchDocument(Document searchDocument) {
		this.searchDocument = searchDocument;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Long getModId() {
		return modId;
	}

	public void setModId(Long modId) {
		this.modId = modId;
	}

	public int getCommentaireCount() {
		return commentaireCount;
	}

	public void setCommentaireCount(int commentaireCount) {
		this.commentaireCount = commentaireCount;
	}

	public OutputStream getOutStream() {
		return outStream;
	}

	public void setAllPending(Collection<Modification> allPending) {
		this.allPending = allPending;
	}

	public int getPendingTextCount() {
		return pendingTextCount;
	}

	public void setPendingTextCount(int pendingTextCount) {
		this.pendingTextCount = pendingTextCount;
	}

	public int getPendingImageCount() {
		return pendingImageCount;
	}

	public void setPendingImageCount(int pendingImageCount) {
		this.pendingImageCount = pendingImageCount;
	}

	public int getPendingVideoCount() {
		return pendingVideoCount;
	}

	public void setPendingVideoCount(int pendingVideoCount) {
		this.pendingVideoCount = pendingVideoCount;
	}

	public int getPendingRawFileCount() {
		return pendingRawFileCount;
	}

	public void setPendingRawFileCount(int pendingRawFileCount) {
		this.pendingRawFileCount = pendingRawFileCount;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

	public void setOutStream(OutputStream outStream) {
		this.outStream = outStream;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewDocumentText() {
		return newDocumentText;
	}

	public void setNewDocumentText(String newDocumentText) {
		this.newDocumentText = newDocumentText;
	}

	/*--------------- nouveau Setters et Getters--------------------*/
	public int getNouvelleNote() {
		return nouvelleNote;
	}

	public void setNouvelleNote(int nouvelleNote) {
		this.nouvelleNote = nouvelleNote;
	}

	public String getTagsSelected() {
		return tagsSelected;
	}

	public void setTagsSelected(String tagsSelected) {
		this.tagsSelected = tagsSelected;
	}

	/* fin setters et getters */


	public int getHistoriqueCount() {
		return historiqueCount;
	}


	public void setHistoriqueCount(int historiqueCount) {
		this.historiqueCount = historiqueCount;
	}


	public List getAllTags() {
		return allTags;
	}


	public void setAllTags(List allTags) {
		this.allTags = allTags;
	}


	public InputStream getDocStream() {
		return docStream;
	}


	public void setDocStream(InputStream docStream) {
		this.docStream = docStream;
	}

	public Collection<Document> getAllDocToShowAfterFilter() {
		return allDocToShowAfterFilter;
	}
	
	public void setAllDocToShowAfterFilter(
			Collection<Document> allDocToShowAfterFilter) {
		this.allDocToShowAfterFilter = allDocToShowAfterFilter;
	}

	public Collection<Tag> getAllTagToShowAfterFilter() {
		return allTagToShowAfterFilter;
	}

	public void setAllTagToShowAfterFilter(
			Collection<Tag> allTagToShowAfterFilter) {
		this.allTagToShowAfterFilter = allTagToShowAfterFilter;
	}

	public Collection<String> getAllTagsSelected() {
		return allTagsSelected;
	}

	public void setAllTagsSelected(Collection<String> allTagsSelected) {
		this.allTagsSelected = allTagsSelected;
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

	public Collection<Document> getNLastImage() {
		return nLastImage;
	}

	public void setNLastImage(Collection<Document> lastImage) {
		nLastImage = lastImage;
	}

	public Collection<Document> getNLastDocument() {
		return nLastDocument;
	}

	public void setNLastDocument(Collection<Document> lastDocument) {
		nLastDocument = lastDocument;
	}

	public Collection<Document> getNLastVideo() {
		return nLastVideo;
	}

	public void setNLastVideo(Collection<Document> lastVideo) {
		nLastVideo = lastVideo;
	}

	public int getTextCountLatest() {
		return textCountLatest;
	}

	public void setTextCountLatest(int textCountLatest) {
		this.textCountLatest = textCountLatest;
	}

	public int getImageCountLatest() {
		return imageCountLatest;
	}

	public void setImageCountLatest(int imageCountLatest) {
		this.imageCountLatest = imageCountLatest;
	}

	public int getRawfileCountLatest() {
		return rawfileCountLatest;
	}

	public void setRawfileCountLatest(int rawfileCountLatest) {
		this.rawfileCountLatest = rawfileCountLatest;
	}

	public int getVideoCountLatest() {
		return videoCountLatest;
	}

	public void setVideoCountLatest(int videoCountLatest) {
		this.videoCountLatest = videoCountLatest;
	}



	


	
}
