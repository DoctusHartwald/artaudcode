package com.voleo.dao.document;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.forum.NotificationWallToWall;

@Component
public class NotificationCommentaireDAO extends AbstractDAO<NotificationCommentaire> implements INotificationCommentaireDAO{

	public NotificationCommentaireDAO() {
		super(NotificationCommentaire.class);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<NotificationCommentaire> getNLastNotificationCommentaire(final int amount) {
		return (Collection)jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery("from NotificationCommentaire n order by n.createDate desc");
				query.setMaxResults(amount);
				return query.getResultList();
			}
		});
	}
	
}
