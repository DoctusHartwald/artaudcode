package com.voleo.dao.forum;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.stereotype.Component;
import com.voleo.dao.AbstractDAO;
import com.voleo.entity.document.Document;
import com.voleo.entity.forum.NotificationForum;

@Component
public class NotificationForumDAO extends AbstractDAO<NotificationForum> implements INotificationForumDAO{

	public NotificationForumDAO() {
		super(NotificationForum.class);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<NotificationForum> getNLastNotificationForum(final int amount) {
		return (Collection)jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery("from NotificationForum nf order by nf.createDate desc");
				query.setMaxResults(amount);
				return query.getResultList();
			}
		});
	}
	
}
