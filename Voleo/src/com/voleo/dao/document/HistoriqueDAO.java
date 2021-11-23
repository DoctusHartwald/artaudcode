package com.voleo.dao.document;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.document.Historique;

@Component
public class HistoriqueDAO extends AbstractDAO<Historique>implements IHistoriqueDAO{
	
	public HistoriqueDAO(){
		super(Historique.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Historique> getAllByUserId(Long userId) {
		return jpaTemplate.find("from Historique h " +
				"inner join fetch h.document " +
				"inner join fetch h.user " +
				"where h.userOrigine.id= ?1 order by h.createDate DESC",userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Historique> getHistoriqueById(Long historiqueId){
		return jpaTemplate.find("from "+getEntityClass().getSimpleName()+" h " +
				"inner join fetch h.document " +
				"where h.id = ?1", historiqueId);
	}
	
}
