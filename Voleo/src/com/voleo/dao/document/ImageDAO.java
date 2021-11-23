package com.voleo.dao.document;

import org.springframework.stereotype.Component;

import com.voleo.entity.document.Image;

@Component
public class ImageDAO extends AbstractDocumentDAO<Image> implements IImageDAO {

	public ImageDAO(){
		super(Image.class);
	}
	

}
