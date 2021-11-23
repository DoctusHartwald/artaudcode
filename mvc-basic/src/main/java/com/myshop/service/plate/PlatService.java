package com.myshop.service.plate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myshop.dao.plate.IPlatHome;
import com.myshop.domain.plate.Plat;

@Service("platService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PlatService implements IPlateService{


	@Autowired
	private IPlatHome mPlatDao;

	public Plat findById(Integer id) {
		return mPlatDao.findById(id);
	}

	public Plat merge(Plat detachedInstance) {
		return mPlatDao.merge(detachedInstance);
	}

	public void persist(Plat transientInstance) {
		mPlatDao.persist(transientInstance);
	}

	public void remove(Plat persistentInstance) {
		mPlatDao.remove(persistentInstance);
	}

}
