package com.myshop.dao.plate;

import com.myshop.domain.plate.Plat;

public interface IPlatHome {
	public void persist(Plat transientInstance) ;
	public void remove(Plat persistentInstance) ;
	public Plat merge(Plat detachedInstance) ;
	public Plat findById(Integer id) ;

}
