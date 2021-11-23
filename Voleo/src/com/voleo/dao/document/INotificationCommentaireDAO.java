package com.voleo.dao.document;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.document.NotificationCommentaire;
import com.voleo.entity.forum.NotificationWallToWall;

public interface INotificationCommentaireDAO extends IDAO<NotificationCommentaire>{

	public Collection<NotificationCommentaire> getNLastNotificationCommentaire(final int amount);
}
