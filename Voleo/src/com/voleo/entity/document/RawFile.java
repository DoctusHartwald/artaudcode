package com.voleo.entity.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import com.voleo.entity.bridge.PDFStringBridge;

@Entity
@Indexed(index = "testIndex")
@PrimaryKeyJoinColumn
public class RawFile extends Document {

	// Constant names for lucene fields.
	public static final String CONTENT_FIELD = "content";
	public static final String DESCRIPTION_FIELD = "description";
	
	
	@Transient
	public String getType() { return DocumentType.RAWFILE.toString(); }

	
	@Lob
	private String url;

	@Field(name = DESCRIPTION_FIELD, index = Index.TOKENIZED)
	private String description;
	
	@Lob
	@Column(columnDefinition="LONGBLOB")
	@Field(name = CONTENT_FIELD, index = Index.TOKENIZED)
	@FieldBridge(impl = PDFStringBridge.class)
	private byte[] content;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
}
