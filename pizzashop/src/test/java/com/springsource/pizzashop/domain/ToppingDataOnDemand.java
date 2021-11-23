package com.springsource.pizzashop.domain;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.springsource.pizzashop.domain.Topping;

@Component
@Configurable
@RooDataOnDemand(entity = Topping.class)
public class ToppingDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Topping> data;

	public Topping getNewTransientTopping(int index) {
        com.springsource.pizzashop.domain.Topping obj = new com.springsource.pizzashop.domain.Topping();
        obj.setName("name_" + index);
        return obj;
    }

	public Topping getSpecificTopping(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size()-1)) index = data.size() - 1;
        Topping obj = data.get(index);
        return Topping.findTopping(obj.getId());
    }

	public Topping getRandomTopping() {
        init();
        Topping obj = data.get(rnd.nextInt(data.size()));
        return Topping.findTopping(obj.getId());
    }

	public boolean modifyTopping(Topping obj) {
        return false;
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void init() {
        if (data != null) {
            return;
        }
        
        data = com.springsource.pizzashop.domain.Topping.findToppingEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Topping' illegally returned null");
        if (data.size() > 0) {
            return;
        }
        
        data = new java.util.ArrayList<com.springsource.pizzashop.domain.Topping>();
        for (int i = 0; i < 10; i++) {
            com.springsource.pizzashop.domain.Topping obj = getNewTransientTopping(i);
            obj.persist();
            data.add(obj);
        }
    }
}
