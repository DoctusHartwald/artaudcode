package com.voleo.dao.document;

import org.springframework.stereotype.Component;

import com.voleo.entity.document.TextModification;

@Component
public class TextModificationDAO extends AbstractModificationDAO<TextModification> implements ITextModificationDAO {

	public TextModificationDAO(){
		super(TextModification.class);
	}	
}
