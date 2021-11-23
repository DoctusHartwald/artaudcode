package com.objis.springmvcdemo.service;

import com.objis.springmvcdemo.domaine.Plat;

public interface IPlatService {

	public void persist(Plat transientInstance) ;
	
	public void remove(Plat persistentInstance) ;

	public Plat merge(Plat detachedInstance) ;
	
	public Plat findById(int id) ;

		
}
