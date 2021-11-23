package com.voleo.dao.document;

import org.springframework.stereotype.Component;
import com.voleo.entity.document.VideoModification;

@Component
public class VideoModificationDAO extends AbstractModificationDAO<VideoModification> implements IVideoModificationDAO {

	public VideoModificationDAO(){
		super(VideoModification.class);
	}	

}
