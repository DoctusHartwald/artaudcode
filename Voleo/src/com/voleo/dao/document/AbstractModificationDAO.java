package com.voleo.dao.document;

import java.util.Collection;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.document.Modification;

public abstract class AbstractModificationDAO<T extends Modification> extends AbstractDAO<T> 
implements IModificationDAO<T> {

	public AbstractModificationDAO(Class<T> entityClass) {
		super(entityClass);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Modification> getByTargetDocumentUserId(Long userId) {
		return jpaTemplate.find("from " + getEntityClass().getSimpleName()+" m " +
				"inner join fetch m.user " +
				"where m.targetDocument.user.id = ?1", userId);
	}
	
	@Override
	public int getCountPendingByUserId(Long userId) {
		return ((Long)jpaTemplate.find("select count(*) from " + getEntityClass().getSimpleName() + " m " +
				"where m.targetDocument.user.id = ?1", userId).get(0)).intValue();
	}
	
	@Override
	public int getCountAllPendingByUserId(Long userId) {
		return ((Long)jpaTemplate.find("select count(*) from Modification m where " +
				"m.targetDocument.user.id = ?1", userId).get(0)).intValue();
	}
	
/*
	@SuppressWarnings("unchecked")
	public Collection<Modification> getByTargetDocumentUserId(Long userId) {
		return jpaTemplate.find("from " + getEntityClass().getSimpleName() + 
				" m where m.targetDocument.user.id = ?1", userId);
	}*/


}
