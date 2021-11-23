package com.voleo.dao.document;

import org.springframework.stereotype.Component;

import com.voleo.entity.document.RawFileModification;

@Component
public class RawFileModificationDAO extends AbstractModificationDAO<RawFileModification> implements IRawFileModificationDAO {

	public RawFileModificationDAO(){
		super(RawFileModification.class);
	}	

}
