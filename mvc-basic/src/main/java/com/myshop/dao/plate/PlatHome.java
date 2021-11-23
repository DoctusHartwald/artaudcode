package com.myshop.dao.plate;

// Generated 19 sept. 2010 16:22:36 by Hibernate Tools 3.2.4.GA

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.myshop.domain.plate.Plat;


/**
 * Home object for domain model class Plat.
 * @see com.myshop.domain.plate.Plat
 * @author Hibernate Tools
 */
@Repository
public class PlatHome extends AbstractDAO<Plat> implements IPlatHome {

	private static final Log log = LogFactory.getLog(PlatHome.class);

	 
	public PlatHome(){
		super(Plat.class);
	}


	public Plat findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Plat merge(Plat detachedInstance) {
		// TODO Auto-generated method stub
		return null;
	}


}
