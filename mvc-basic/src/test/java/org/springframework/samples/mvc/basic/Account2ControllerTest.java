package org.springframework.samples.mvc.basic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myshop.domain.plate.Plat;
import com.myshop.service.plate.IPlateService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:/META-INF/spring/myshop-data.xml"})
public class Account2ControllerTest {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    @Qualifier("platService")
    private IPlateService mplathome ; 
    
    @Test
    public void test2(){
	Plat plat1 = new Plat("Carpacio XL"," Carpacio et pates a volonte!! Delichieux filet de ","heyehey ");
		
		Plat plat2 = new Plat("vChoco","Deliciosooo !!Delichieux filet de ","heyehey ");
		
		mplathome.persist(plat1);
		mplathome.persist(plat2);
    }
}
