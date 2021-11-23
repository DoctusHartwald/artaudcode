package com.voleo.dao.forum;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.forum.Reponse;

@Component
public class ReponseDAO extends AbstractDAO<Reponse> implements IReponseDAO{

	public ReponseDAO() {
		super(Reponse.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Reponse> getResponseDiscussion(Long forumId){
		return 	jpaTemplate.find("from Reponse r " +
				"inner join fetch r.user " +
				"where r.forum.id = ?1",forumId);
	}
	
	
	@Override
	public int getCountAllReponseByForumId(Long forumId) {
		return ((Long)jpaTemplate.find("select count(*) from Reponse r where " +
				"r.forum.id = ?1", forumId).get(0)).intValue();
	}
}
