package com.voleo.dao.document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.dto.admin.DateCountCommentaire;
import com.voleo.dto.admin.PseudoInfo;
import com.voleo.entity.document.Commentaire;
import com.voleo.entity.user.User;


@Component
public class CommentaireDAO  extends AbstractDAO<Commentaire>  implements ICommentaireDAO{

	public CommentaireDAO(){
		super(Commentaire.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Commentaire> getAllCommentsByDocumentId(Long docId) {
		return jpaTemplate.find("from "+getEntityClass().getSimpleName()+" c " +
				"inner join fetch c.user " +
				"where c in" +
				"(select elements(d.commentaires) from Document d where d.id = ?1)",docId);
	}
	
	@Override
	public int getCountCommentBydocId(Long docId) {
		return ((Long)jpaTemplate.find("select count(*) from " + getEntityClass().getSimpleName() + " c where " +
				"c.document.id = ?1", docId).get(0)).intValue();
	}
	
	@Override
	public Collection<User> getDocumentsVoteByUserIdComId(Long userId, Long comId){
		return jpaTemplate.find("select c.userWhoHasVoted from Commentaire c " +
				"left outer join c.userWhoHasVoted cv " +
				"where c.id = ?2 and cv.id = ?1 ", userId, comId);
	}
	
	@Override
	public Commentaire getCommentaireById(Long comId){
		return (Commentaire)jpaTemplate.find("from Commentaire c " +
				"where c.id= ?1",comId).get(0);
	}
	
//////////////A Copier 
	////A Ajouter Pour la Partie Graph
	@Override
	public Collection<DateCountCommentaire> getdateCountCommentaire() {
		List<DateCountCommentaire> dateCountCommentaires = new ArrayList<DateCountCommentaire>();
		List<Object[]> results =  jpaTemplate.find("select c.dateFormat,count(*),c.createDate from Commentaire c group by c.dateFormat");
		for(Object[] result : results ){
			DateCountCommentaire dateCommentaire = new DateCountCommentaire();
			dateCommentaire.setDateFormat((String)result[0]);
			dateCommentaire.setCountcommentaire(((Number)result[1]).intValue());
			dateCommentaire.setDate((Date)result[2]);
			dateCountCommentaires.add(dateCommentaire);
		}
		return dateCountCommentaires;
	}
	
	public Collection<PseudoInfo> surveillance(Long userId){
		Long id = userId ; 
		List<PseudoInfo> pseudoInfos = new ArrayList<PseudoInfo>();
		List<Object[]> results = jpaTemplate.find("select d.name,u.pseudo from Document as d , User as u where u.id=?1))",id);
		
		for (Object[]  result : results) {
			PseudoInfo pseudoInfo = new PseudoInfo();
			pseudoInfo.setCommentaire((String)result[0]);
			pseudoInfo.setName((String)result[1]);
			pseudoInfos.add(pseudoInfo);
		}
		return pseudoInfos;
	}
	
	@Override
	public Collection<User> getAllCommentairesVoteByUserId(Long comId){
		return jpaTemplate.find("select c.userWhoHasVoted from Commentaire c " +
				"where c.id = ?1 ", comId);
	}
}
