package com.voleo.dao.document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

import com.voleo.dao.AbstractDAO;
import com.voleo.dto.admin.DateCountDocument;
import com.voleo.dto.admin.DateCountImage;
import com.voleo.dto.admin.DateCountVideo;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentStatus;
import com.voleo.entity.document.Tag;
import com.voleo.entity.user.User;

public abstract class AbstractDocumentDAO<T extends Document> extends AbstractDAO<T> 
implements IDocumentDAO<T> {

	public AbstractDocumentDAO(Class<T> entityClass) {
		super(entityClass);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> getAllByUser(User user) {
		return getAllByUserId(user.getId());
	}

	
	@SuppressWarnings("unchecked")
	public Collection<T> getAllByUserId(Long userId) {
		return jpaTemplate.find("from "+getEntityClass().getSimpleName()+" d " +
				"where d.user.id = ?1", userId);
	}
	//"inner join fetch d.tags " +
	@SuppressWarnings("unchecked")
	public Collection<T> getAllOtherDocument(Long userId) {
		return jpaTemplate.find("from "+getEntityClass().getSimpleName()+" d " +
				"where d in " +
				"(select elements(u.documents) from User u where u.id != ?1)",userId);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> getAllValidated(Long userId) {
		return jpaTemplate.find("from "+getEntityClass().getSimpleName()+" d " +
				"where d.user.id = ?1 AND d.status = ?2",
				userId, DocumentStatus.VALIDATED);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> getAllPending(Long userId) {
		return jpaTemplate.find("from "+getEntityClass().getSimpleName()+" d " +
				"where d.user.id = ?1 AND d.status = ?2",
				userId, DocumentStatus.PENDING);
	}
	
	@Override
	public int getCountByUser(Long userId) {
		return ((Long)jpaTemplate.find("select count(*) from " + getEntityClass().getSimpleName() + " doc where " +
				"doc.user.id = ?1", userId).get(0)).intValue();
	}

	@Override
	public int getCountAllByUser(Long userId) {
		return ((Long)jpaTemplate.find("select count(*) from Document doc where " +
				"doc.user.id = ?1", userId).get(0)).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Document> getNLastByUser(final Long userId, final int amount) {
		return (Collection)jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery("from " 
						+ getEntityClass().getSimpleName() 
						+ " doc where doc.user.id = :userId "
						+ "order by doc.createDate desc");
				query.setParameter("userId", userId);
				query.setMaxResults(amount);
				return query.getResultList();
			}
		});
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Document> getNLastDocument(final int amount) {
		return (Collection)jpaTemplate.execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) {
				Query query = em.createQuery("from " 
						+ getEntityClass().getSimpleName() 
						+ " doc order by doc.createDate desc");
				query.setMaxResults(amount);
				return query.getResultList();
			}
		});
	}
	
	/* modifié */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> getDocumentsVoteByUserIdDocId(Long userId,Long docId) {
		return jpaTemplate.find("select d.userWhoHasVoted from "+getEntityClass().getSimpleName()+" d " +
				"left outer join d.userWhoHasVoted dv " +
				"where d.id = ?2 and dv.id = ?1 ", userId, docId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> getDocumentsVoteByUserIdDocIdTest(Long userId,Long docId) {
		return jpaTemplate.find("select d.userWhoHasVoted from Document d " +
				"left outer join d.userWhoHasVoted dv " +
				"where d.id = ?2 and dv.id = ?1 ", userId, docId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Document> getDocumentsByTagsName(Collection<Tag> listTags){
		if(listTags.isEmpty())
			return null;
		String request = "select distinct d from Document d left outer join d.tags t left outer join fetch d.user u where";
		String requestTemp = " d.id in (select d.id from Document d left outer join d.tags t where t.name = ";		
		for(Tag i : listTags)
			request += requestTemp +"'"+ i.getName()+ "') and";
		request = request.substring(0, request.length()- 4);
		return jpaTemplate.find(request);
	}
	/* fin modifié */
	//Ajout pr partie graphique 
	public Collection<DateCountDocument> getDateCountDocument(){
		List<DateCountDocument> dateCountDocuments = new ArrayList<DateCountDocument>();
		List<Object[]> results =  jpaTemplate.find("select d.dateFormat,count(*),d.createDate from Document d group by d.dateFormat");
		for(Object[] result : results){
			DateCountDocument dateCountDocument = new DateCountDocument() ;
			dateCountDocument.setDateFormat((String)result[0]);
			dateCountDocument.setCountNumberDocument(((Number)result[1]).intValue());
			dateCountDocument.setDate((Date)result[2]);
			dateCountDocuments.add(dateCountDocument);
		}
		return dateCountDocuments; 
	}
	public Collection<DateCountImage> getDateCountImage(){
		List<DateCountImage> dateCountImages = new ArrayList<DateCountImage>();
		
		List<Object[]> results =  jpaTemplate.find("select d.dateFormat,count(*),d.createDate from Image d group by d.dateFormat");
		for (Object[] result : results) {
			DateCountImage dateCountImage = new DateCountImage();
			dateCountImage.setDateFormat((String)result[0]);
			dateCountImage.setCountNumberDocument(((Number)result[1]).intValue());
			dateCountImage.setDate((Date)result[2]);
			dateCountImages.add(dateCountImage);
		}
		return dateCountImages;
		
	}
	public Collection<DateCountVideo> getDateCountVideo(){
		List<DateCountVideo> dateCountVideos = new ArrayList<DateCountVideo>();
		List<Object[]> results = jpaTemplate.find("select d.dateFormat,count(*),d.createDate from Video d group by d.dateFormat");
		for (Object[] result : results) {
			DateCountVideo dateCountVideo = new DateCountVideo();
			dateCountVideo.setDateFormat((String)result[0]);
			dateCountVideo.setCountNumberDocument(((Number)result[1]).intValue());
			dateCountVideo.setDate((Date)result[2]);
			dateCountVideos.add(dateCountVideo);
		}
		return dateCountVideos ; 
	}
	//Fin graphique 
}
