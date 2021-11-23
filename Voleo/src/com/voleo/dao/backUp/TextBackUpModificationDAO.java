package com.voleo.dao.backUp;

import org.springframework.stereotype.Component;
import com.voleo.entity.backUp.TextBackUpModification;

@Component
public class TextBackUpModificationDAO extends AbstractBackUpModificationDAO<TextBackUpModification> implements ITextBackUpModificationDAO {

	public TextBackUpModificationDAO(){
		super(TextBackUpModification.class);
	}
	
}
