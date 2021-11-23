package com.voleo.entity.document;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed(index = "testIndex")
@PrimaryKeyJoinColumn
public class Text extends Document {
	public static final String TEXT_FIELD = "text";
	
	@Transient
	public String getType() { return DocumentType.TEXT.toString(); }
	
	@Lob
	@Field(name = TEXT_FIELD, index = Index.TOKENIZED)
	private String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (modification != null && isDifferent(this.text, text)) {
			((TextModification)modification).setText(text);
		}
		this.text = text;
	}
	
	
}
