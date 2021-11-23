package com.myshop.dao.plate;

import com.myshop.domain.plate.Person;


public interface IPersonDao {
	public void persist(Person transientInstance) ;
	public void remove(Person persistentInstance) ;
	public Person merge(Person detachedInstance) ;
	public Person findById(Integer id) ;

}
