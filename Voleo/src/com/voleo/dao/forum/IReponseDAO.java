package com.voleo.dao.forum;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.forum.Reponse;

public interface IReponseDAO extends IDAO<Reponse>{

	public Collection<Reponse> getResponseDiscussion(Long forumId);
	public int getCountAllReponseByForumId(Long forumId);
}
