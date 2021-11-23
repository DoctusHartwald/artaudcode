package com.objis.springmvcdemo.dao;

// Generated 14 sept. 2010 22:49:51 by Hibernate Tools 3.2.4.GA


import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import com.objis.springmvcdemo.domaine.Plat;

/**
 * Home object for domain model class Plat.
 * @see com.objis.springmvcdemo.domaine.Plat
 * @author Hibernate Tools
 */
@Repository("platHome")
public class PlatHome  implements IPlatHome {

	private static final Log log = LogFactory.getLog(PlatHome.class);

//	@PersistenceContext
//	private EntityManager entityManager;

    protected JpaTemplate jpaTemplate;
 
	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
            this.jpaTemplate = new JpaTemplate(emf);
    }

	
	public void persist(Plat transientInstance) {
		log.debug("persisting Plat instance");
		try {
			jpaTemplate.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Plat persistentInstance) {
		log.debug("removing Plat instance");
		try {
			jpaTemplate.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Plat merge(Plat detachedInstance) {
		log.debug("merging Plat instance");
		try {
			Plat result = jpaTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Plat findById(int id) {
		log.debug("getting Plat instance with id: " + id);
		try {
			System.out.println("findByID DAO === ");
			Plat instance = jpaTemplate.find(Plat.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
