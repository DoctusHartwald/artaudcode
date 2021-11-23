package com.voleo.service;

import java.util.Collection;

import com.voleo.dto.admin.PseudoInfo;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.user.User;

public interface ICommentaireService {

	public Collection<Commentaire> getAllCommentsByDocumentId(Long docId);
	public void addCommentaire(Commentaire commentaire,DocumentType docType,Long docId,Long userId);
	public int getCountCommentBydocId(Long docId);
	public void addNotificationCommentaire(Commentaire commentaire,DocumentType docType,Long docId,Long userId);
	public Collection<NotificationCommentaire> getNLastNotificationCommentaire(int amount);
	public Collection<NotificationCommentaire> getAllNotificationCommentaire();
	public void deleteNotificationCommentaire(Long notificationCommentaireId);
	public Collection<PseudoInfo> surveillance(Long userId);
	public Collection<User> getDocumentsVoteByUserIdComId(Long userId, Long comId);
	public Commentaire getCommentaireById(Long comId);
	public void ajoutNoteCommentaire(Commentaire com, int nouvelleNote);
	void voteCommentaire(Long userId, Commentaire com);
}
