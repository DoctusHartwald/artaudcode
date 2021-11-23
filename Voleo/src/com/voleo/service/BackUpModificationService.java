package com.voleo.service;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.backUp.IBackUpModificationDAO;
import com.voleo.dao.backUp.ITextBackUpModificationDAO;
import com.voleo.dao.document.IDocumentDAO;
import com.voleo.dao.document.IHistoriqueDAO;
import com.voleo.dao.document.IImageDAO;
import com.voleo.dao.document.IRawFileDAO;
import com.voleo.dao.document.ITextDAO;
import com.voleo.dao.document.IVideoDAO;
import com.voleo.entity.backUp.BackUpModification;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.Historique;

@Component
@Transactional
public class BackUpModificationService implements IBackUpModificationService{

	private Map<DocumentType, IDocumentDAO> docDaoMap; 
	private Map<DocumentType, IBackUpModificationDAO> backUpModDaoMap;

	@Autowired 
	private ITextBackUpModificationDAO textBackUpModificationDAO;
	
	@Autowired
	private ITextDAO textDAO;
	
	@Autowired
	private IImageDAO imageDAO;

	@Autowired
	private IVideoDAO videoDAO;

	@Autowired
	private IRawFileDAO rawFileDAO;
	
	@Autowired
	private IHistoriqueDAO historiqueDAO;
	
	
	@PostConstruct
	public void initDaoMap() {
		docDaoMap = new EnumMap<DocumentType, IDocumentDAO>(DocumentType.class);
		docDaoMap.put(DocumentType.TEXT, textDAO);
		docDaoMap.put(DocumentType.IMAGE, imageDAO);
		docDaoMap.put(DocumentType.VIDEO, videoDAO);
		docDaoMap.put(DocumentType.RAWFILE, rawFileDAO);
		
		backUpModDaoMap = new EnumMap<DocumentType, IBackUpModificationDAO>(DocumentType.class);
		backUpModDaoMap.put(DocumentType.TEXT, textBackUpModificationDAO);
	}
	private Collection<Historique> allHistorique;
	private Historique historique;
	private Long docId;
	private Document doc; 
	
	private IBackUpModificationDAO getBackUpModDAOForDocument(Document doc) {
		return backUpModDaoMap.get(DocumentType.getByClass(doc.getClass()));
	}
	
	private IDocumentDAO getDocDAOForDocument(Document doc) {
		return docDaoMap.get(DocumentType.getByClass(doc.getClass()));
	}
	
	

	@Override
	public void supprimerBackModificationById(Long id){
//		allHistorique = historiqueDAO.getHistoriqueById(id);
//		for(Historique i:allHistorique)
//			 doc = i.getDocument();
//
//		
		
//		backUpModDaoMap.get(doctype);
//		getBackUpModDAOForDocument(doc);
	//	IBackUpModificationDAO backUpDAO = getBackUpModDAOForDocument(doc);
		System.out.println("ID hist: "+id);
		historiqueDAO.removeById(id);
		textBackUpModificationDAO.removeById(id);
		
	}
	
	
	@Override
	public BackUpModification getBackUpModification(Long id){
		IBackUpModificationDAO backUpDAO = backUpModDaoMap.get(DocumentType.TEXT);
		return (BackUpModification)backUpDAO.getById(id);
	}

	
}
