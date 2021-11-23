package com.voleo.dao.document;

import org.springframework.stereotype.Component;

import com.voleo.entity.document.ImageModification;

@Component
public class ImageModificationDAO extends AbstractModificationDAO<ImageModification> implements IImageModificationDAO {

	public ImageModificationDAO(){
		super(ImageModification.class);
	}	
}
