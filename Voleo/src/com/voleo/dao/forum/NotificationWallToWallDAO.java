package com.voleo.dao.forum;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.stereotype.Component;
import com.voleo.dao.AbstractDAO;
import com.voleo.entity.forum.NotificationWallToWall;

@Component
public class NotificationWallToWallDAO extends AbstractDAO<NotificationWallToWall> implements INotificationWallToWallDAO{

	public NotificationWallToWallDAO() {
		super(NotificationWallToWall.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<NotificationWallToWall> getNLastNotificationWallToWall(final int amount) {
		return (Collection)jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery("from NotificationWallToWall n order by n.createDate desc");
				query.setMaxResults(amount);
				return query.getResultList();
			}
		});
	}
}
