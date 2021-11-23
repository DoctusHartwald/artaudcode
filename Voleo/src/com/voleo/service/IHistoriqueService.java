package com.voleo.service;

import java.util.Collection;

import com.voleo.entity.user.User;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.Historique;

public interface IHistoriqueService {
	public void addHistorique(User userOrigine,User userHistorique,Document doc);
	public Collection<Historique> getAllByUserId(Long userId);
	public int getCountHistoriqueByUser(Long userId);
}
