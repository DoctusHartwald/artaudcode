package com.voleo.dao.document;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.document.Historique;

public interface IHistoriqueDAO extends IDAO<Historique>{
	public Collection<Historique> getAllByUserId(Long userId);
	
	public Collection<Historique> getHistoriqueById(Long historiqueId);
}
