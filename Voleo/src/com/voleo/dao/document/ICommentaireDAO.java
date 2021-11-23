package com.voleo.dao.document;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.dto.admin.DateCountCommentaire;
import com.voleo.dto.admin.PseudoInfo;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.user.User;

public interface ICommentaireDAO extends IDAO<Commentaire>{
	public Collection<Commentaire> getAllCommentsByDocumentId(Long docId);
	public int getCountCommentBydocId(Long docId);
	public Collection<DateCountCommentaire> getdateCountCommentaire();
	public Collection<PseudoInfo> surveillance(Long userId);
	public Collection<User> getDocumentsVoteByUserIdComId(Long userId, Long comId);
	public Commentaire getCommentaireById(Long comId);
	Collection<User> getAllCommentairesVoteByUserId(Long comId);
}
