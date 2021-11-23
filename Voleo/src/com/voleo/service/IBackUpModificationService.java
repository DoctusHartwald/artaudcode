package com.voleo.service;

import com.voleo.entity.backUp.BackUpModification;

public interface IBackUpModificationService {

	public BackUpModification getBackUpModification(Long id);
	
	public void supprimerBackModificationById(Long id);
}
