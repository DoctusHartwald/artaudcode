package com.springsource.pizzashop.domain;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.springsource.pizzashop.domain.Base;

@Component
@Configurable
@RooDataOnDemand(entity = Base.class)
public class BaseDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Base> data;

	public Base getNewTransientBase(int index) {
        com.springsource.pizzashop.domain.Base obj = new com.springsource.pizzashop.domain.Base();
        obj.setName("name_" + index);
        return obj;
    }

	public Base getSpecificBase(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        Base obj = data.get(index);
        return Base.findBase(obj.getId());
    }

	public Base getRandomBase() {
        init();
        Base obj = data.get(rnd.nextInt(data.size()));
        return Base.findBase(obj.getId());
    }

	public boolean modifyBase(Base obj) {
        return false;
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void init() {
        if (data != null) {
            return;
        }
        
        data = com.springsource.pizzashop.domain.Base.findBaseEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Base' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<com.springsource.pizzashop.domain.Base>();
        for (int i = 0; i < 10; i++) {
            com.springsource.pizzashop.domain.Base obj = getNewTransientBase(i);
            obj.persist();
            data.add(obj);
        }
    }
}
