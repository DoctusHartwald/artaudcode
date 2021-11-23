package com.voleo.entity.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
@PrimaryKeyJoinColumn
public class Video extends Document {

	
	@Transient
	public String getType() { return DocumentType.VIDEO.toString(); }

	
	@Lob
	@Column(columnDefinition="LONGBLOB")
	private byte[] video;

	public byte[] getVideo() {
		return video;
	}

	public void setVideo(byte[] video) {
		this.video = video;
	}
}
