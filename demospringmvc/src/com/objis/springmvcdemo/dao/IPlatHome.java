package com.objis.springmvcdemo.dao;

import com.objis.springmvcdemo.domaine.Plat;

public interface IPlatHome {
	public void persist(Plat transientInstance) ;

	public void remove(Plat persistentInstance) ;

	public Plat merge(Plat detachedInstance) ;

	public Plat findById(int id) ;


}
