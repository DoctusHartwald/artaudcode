package com.voleo.dao.document;

import org.springframework.stereotype.Component;

import com.voleo.entity.document.RawFile;

@Component
public class RawFileDAO extends AbstractDocumentDAO<RawFile> implements IRawFileDAO {

	public RawFileDAO(){
		super(RawFile.class);
	}
	

}
