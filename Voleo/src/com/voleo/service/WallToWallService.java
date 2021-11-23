package com.voleo.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.forum.INotificationWallToWallDAO;
import com.voleo.dao.forum.IWallToWallDAO;
import com.voleo.entity.forum.NotificationWallToWall;
import com.voleo.entity.forum.WallToWall;

@Component
@Transactional
public class WallToWallService implements IWallToWallService{

	@Autowired 
	private IWallToWallDAO wallToWallDAO;
	
	@Autowired 
	private INotificationWallToWallDAO notificationWallToWallDAO;

	@Override
	public void addWallToWall(WallToWall wallToWall){
		Date datecreation = new Date();
		wallToWall.setCreateDate(datecreation);
		wallToWall.setView(0);
		wallToWallDAO.add(wallToWall);
	}
	
	@Override
	public void addNotificationWallToWall(NotificationWallToWall notificationWallToWall){
		Date datecreation = new Date();
		notificationWallToWall.setCreateDate(datecreation);
		
		notificationWallToWallDAO.add(notificationWallToWall);
	}
	
	public Collection<WallToWall> getAllMyReceivedWallToWall(Long userId){
		return wallToWallDAO.getAllMyReceivedWallToWall(userId);
	}
	
	@Override
	public void deleteWallToWall(Long wallToWallId){
		wallToWallDAO.removeById(wallToWallId);
	}
	
	@Override
	public Collection<NotificationWallToWall> getNLastNotificationWallToWall(int amount){
		return notificationWallToWallDAO.getNLastNotificationWallToWall(amount);
	}
	
	@Override
	public WallToWall getWallToWallById(Long wallToWallId){
		return wallToWallDAO.getById(wallToWallId);
	}
}
