package com.voleo.dao.backUp;

import java.util.Collection;
import java.util.List;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.backUp.BackUpModification;

public class AbstractBackUpModificationDAO <T extends BackUpModification> extends AbstractDAO<T> 
implements IBackUpModificationDAO<T> {

	public AbstractBackUpModificationDAO(Class<T> entityClass) {
		super(entityClass);
	}

	@Override
	public int getCountHistoriqueByUser(Long userId) {
		return ((Long)jpaTemplate.find("select count(*) from BackUpModification b where " +
				"b.targetDocument.user.id = ?1", userId).get(0)).intValue();
	}
	@Override
	public T getBackUpModificationByHistoriqueId(Long historiqueId){
		List<T> results = jpaTemplate.find("from BackUpModification b where " +
				"b.historique.id = ?1", historiqueId);
		if (results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}
	
}
