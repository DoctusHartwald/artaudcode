package com.voleo.entity.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed(index = "testIndex")
@PrimaryKeyJoinColumn
public class Image extends Document {
	
	
	@Transient
	public String getType() { return DocumentType.IMAGE.toString(); }

	@Lob
	@Column(columnDefinition="LONGBLOB")
	private byte[] image;

	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}


	
	
}
