package com.voleo.dao.forum;

import java.util.Collection;
import java.util.List;

import com.voleo.dao.IDAO;
import com.voleo.dto.admin.DateCountForum;
import com.voleo.entity.forum.Forum;

public interface IForumDAO extends IDAO<Forum>{
	public Collection<Forum> getAllDiscussion();
	public Forum getForumDiscussion(Long forumId);
	public int getCountDiscussionByForumId();
	public int getCountNewDiscussionByForumId();
	public Collection<Forum> myDiscussions(Long userId);
	public Collection<Forum> DiscussionSansReponse();
	///Graph Part 
	Collection<DateCountForum> getDateCountForum();
	/*Graph*/
}
