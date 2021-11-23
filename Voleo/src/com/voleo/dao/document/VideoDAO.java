package com.voleo.dao.document;

import org.springframework.stereotype.Component;

import com.voleo.entity.document.Video;

@Component
public class VideoDAO extends AbstractDocumentDAO<Video> implements IVideoDAO {

	public VideoDAO(){
		super(Video.class);
	}
	

}
