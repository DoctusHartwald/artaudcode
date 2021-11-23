package com.voleo.dao.forum;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.forum.WallToWall;

public interface IWallToWallDAO extends IDAO<WallToWall>{

	public Collection<WallToWall> getAllMyReceivedWallToWall(Long userId);
}
