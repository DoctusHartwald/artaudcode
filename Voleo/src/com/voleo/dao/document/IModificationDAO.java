package com.voleo.dao.document;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.document.Modification;

public interface IModificationDAO<T extends Modification> extends IDAO<T> {
	public Collection<Modification> getByTargetDocumentUserId(Long userId);
	public int getCountPendingByUserId(Long userId);
	public int getCountAllPendingByUserId(Long userId);
}
