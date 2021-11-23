package com.voleo.dao.forum;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.forum.NotificationWallToWall;

public interface INotificationWallToWallDAO extends IDAO<NotificationWallToWall>{

	public Collection<NotificationWallToWall> getNLastNotificationWallToWall(final int amount);
}
