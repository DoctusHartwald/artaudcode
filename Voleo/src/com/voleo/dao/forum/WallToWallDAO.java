package com.voleo.dao.forum;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.forum.WallToWall;

@Component
public class WallToWallDAO extends AbstractDAO<WallToWall> implements IWallToWallDAO{

	public WallToWallDAO() {
		super(WallToWall.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<WallToWall> getAllMyReceivedWallToWall(Long userId){
		return 	jpaTemplate.find("from WallToWall w " +
				"inner join fetch w.userDestination " +
				"inner join fetch w.userOrigine " +
				"inner join fetch w.forum " +
				"where w.userDestination.id = ?1 order by w.createDate DESC",userId);
	}
}
