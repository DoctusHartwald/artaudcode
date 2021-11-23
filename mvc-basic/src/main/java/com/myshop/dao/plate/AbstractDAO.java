package com.myshop.dao.plate;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractDAO<T>  {
        
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
        
     

        @SuppressWarnings("unchecked")
        public Collection<T> getAll() {
                return jpaTemplate.find("from " + entityClass.getSimpleName());
        }

       @Transactional
        public void persist(T t) {
        	System.out.println(">>>>>>>>> tititititit"+t.toString());
    		jpaTemplate.persist(t);
    	
    		//jpaTemplate.flush();
    		System.out.println("Fin ");
    	
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

		public JpaTemplate getJpaTemplate() {
			return jpaTemplate;
		}

		public void setJpaTemplate(JpaTemplate jpaTemplate) {
			this.jpaTemplate = jpaTemplate;
		}

}
