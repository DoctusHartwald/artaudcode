package com.voleo.dao.backUp;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.backUp.BackUpModification;

public interface IBackUpModificationDAO<T extends BackUpModification> extends IDAO<T>{
	public int getCountHistoriqueByUser(Long userId);
	public T getBackUpModificationByHistoriqueId(Long historiqueId);
	
}
