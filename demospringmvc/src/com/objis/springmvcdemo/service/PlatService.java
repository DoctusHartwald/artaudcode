package com.objis.springmvcdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.objis.springmvcdemo.dao.IPlatHome;
import com.objis.springmvcdemo.domaine.Plat;


@Service("platService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PlatService implements IPlatService{

	@Autowired
	@Qualifier("platHome")
	private IPlatHome mPlatDao;
	
	
	@Override
	public Plat findById(int id) {
		return  mPlatDao.findById(id);
	}

	@Override
	public Plat merge(Plat detachedInstance) {
		return mPlatDao.merge(detachedInstance);
		
	}

	@Override
	public void persist(Plat transientInstance) {
		System.out.println("Entree dans la couche Service Formulaire");
		mPlatDao.persist(transientInstance);
		
	}

	@Override
	public void remove(Plat persistentInstance) {
		// TODO Auto-generated method stub
		
	}

}
