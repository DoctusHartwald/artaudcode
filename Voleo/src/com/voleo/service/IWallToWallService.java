package com.voleo.service;

import java.util.Collection;

import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.forum.WallToWall;

public interface IWallToWallService {

	public void addWallToWall(WallToWall wallToWall);
	public Collection<WallToWall> getAllMyReceivedWallToWall(Long userId);
	public void deleteWallToWall(Long wallToWallId);
	public void addNotificationWallToWall(NotificationWallToWall notificationWallToWall);
	public Collection<NotificationWallToWall> getNLastNotificationWallToWall(int amount);
	public WallToWall getWallToWallById(Long wallToWallId);
}
