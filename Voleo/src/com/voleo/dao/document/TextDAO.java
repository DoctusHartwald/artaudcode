package com.voleo.dao.document;

import org.springframework.stereotype.Component;

import com.voleo.entity.document.Text;

@Component
public class TextDAO extends AbstractDocumentDAO<Text> implements ITextDAO {

	public TextDAO(){
		super(Text.class);
	}	
}
