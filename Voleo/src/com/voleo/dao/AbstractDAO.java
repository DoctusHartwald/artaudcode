package com.voleo.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;

public abstract class AbstractDAO<T> implements IDAO<T> {
	
	private Class<T> entityClass;
	
	protected JpaTemplate jpaTemplate;

	protected Class<T> getEntityClass() {
		return entityClass;
	}
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.jpaTemplate = new JpaTemplate(emf);
	}

	@SuppressWarnings("unchecked")
	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void add(T t) {
		jpaTemplate.persist(t);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> getAll() {
		return jpaTemplate.find("from " + entityClass.getSimpleName());
	}
	
	public T getById(Long id) {
		return jpaTemplate.find(entityClass, id);
	}
	
	public void remove(T t) {
		jpaTemplate.remove(t);		
	}

	/*
	public void removeById(final Long id) {
		jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) {
				Query q = 
					em.createQuery("delete " + entityClass.getSimpleName() + " e where e.id = :id");
				q.setParameter("id", id);
				q.executeUpdate();
				return null;
			}
		});
	}*/
	
	public void removeById(final Long id) {
		jpaTemplate.remove(jpaTemplate.find(entityClass, id));
		/*jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) {
				Object entity = em.find(entityClass, id);
				em.remove(entity);
				return null;
			}
		});*/
	}
	
	
	public void update(T t) {
		jpaTemplate.merge(t);
	}

}
