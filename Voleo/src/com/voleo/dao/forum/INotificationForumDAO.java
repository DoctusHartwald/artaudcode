package com.voleo.dao.forum;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.document.Document;
import com.voleo.entity.forum.NotificationForum;

public interface INotificationForumDAO extends IDAO<NotificationForum>{

	public Collection<NotificationForum> getNLastNotificationForum(final int amount);
	
}
