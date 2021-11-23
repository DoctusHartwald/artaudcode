package com.myshop.dao.plate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.myshop.domain.plate.Person;
import com.myshop.domain.plate.Plat;


@Repository
public class PersonDao extends AbstractDAO<Person> implements IPersonDao {

	private static final Log log = LogFactory.getLog(PersonDao.class);

	 
	public PersonDao(){
		super(Person.class);
	}


	public Person findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Person merge(Person detachedInstance) {
		// TODO Auto-generated method stub
		return null;
	}




	

}
