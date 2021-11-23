package com.voleo.dao.forum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import com.voleo.dao.AbstractDAO;
import com.voleo.dto.admin.DateCountForum;
import com.voleo.entity.forum.Forum;

@Component
public class ForumDAO extends AbstractDAO<Forum> implements IForumDAO{

	public ForumDAO() {
		super(Forum.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Forum> getAllDiscussion(){
		return 	jpaTemplate.find("from Forum f inner join fetch f.user");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Forum getForumDiscussion(Long forumId){
		List<Forum> results = jpaTemplate.find("from Forum f inner join fetch f.user where f.id = ?1",forumId);
		if (results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public int getCountDiscussionByForumId() {
		return ((Long)jpaTemplate.find("select count(*) from Forum f").get(0)).intValue();
	}
	
	@Override
	public int getCountNewDiscussionByForumId() {
			Calendar cal = Calendar.getInstance();  //gets current date
	        cal.add(Calendar.DATE, -1);             //changes current date to yesterday date
//	        System.out.println(cal.toString());
	        Date yesterday  = cal.getTime(); 
		return ((Long)jpaTemplate.find("select count(*) from Forum f where f.createDate > ?1",yesterday).get(0)).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Forum> myDiscussions(Long userId){
		return jpaTemplate.find("from Forum f inner join fetch f.user where f.user.id = ?1",userId);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Forum> DiscussionSansReponse(){
			return jpaTemplate.find("from Forum f where size(f.reponse) = 0");	
		}
	
	///Graph Part 
	@Override
	public Collection<DateCountForum> getDateCountForum() {
		List<DateCountForum> dateCountForums = new ArrayList<DateCountForum>();
		List<Object[]> results = jpaTemplate.find("select c.dateFormat,count(*),c.createDate from Forum c group by c.dateFormat");
		for (Object[] result : results) {
			DateCountForum dateCountForum = new DateCountForum();
			dateCountForum.setDateFormat((String)result[0]);
			dateCountForum.setCountNumberDocument(((Number)result[1]).intValue());
			dateCountForum.setDate((Date)result[2]);
			dateCountForums.add(dateCountForum);
		}
		
		return dateCountForums;
	}
	/*Graph*/
		
	
}

