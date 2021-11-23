package com.voleo.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.backUp.IBackUpModificationDAO;
import com.voleo.dao.document.IHistoriqueDAO;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Historique;
import com.voleo.entity.user.User;

@Component
@Transactional
public class HistoriqueService implements IHistoriqueService{

	@Autowired 
	private IHistoriqueDAO historiqueDAO;
	
	@Autowired 
	private IBackUpModificationDAO backUpModificationDAO;
	
	public void addHistorique(User userOrigine,User userHistorique,Document doc){
		
		Historique historique = new Historique();
		historique.setDocument(doc);
		historique.setUserOrigine(userOrigine); // User originaire du doc
		historique.setUser(userHistorique); //User ayant modifié le doc
		
		Date datecreation = new Date(); 
		historique.setCreateDate(datecreation);
		
		historiqueDAO.add(historique);
	}
	
	public Collection<Historique> getAllByUserId(Long userId){
		return historiqueDAO.getAllByUserId(userId);
	}
	
	public int getCountHistoriqueByUser(Long userId){
		return backUpModificationDAO.getCountHistoriqueByUser(userId);
	}
	
	
}
