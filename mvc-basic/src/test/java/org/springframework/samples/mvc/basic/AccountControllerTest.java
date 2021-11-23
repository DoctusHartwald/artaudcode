/**
 * 
 */
package org.springframework.samples.mvc.basic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.myshop.controller.plate.PlatController;
import com.myshop.dao.plate.IPersonDao;
import com.myshop.domain.plate.Person;
import com.myshop.domain.plate.Plat;
import com.myshop.service.plate.IPlateService;


/**
 * @author artaud
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:/META-INF/spring/myshop-data.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class AccountControllerTest {

    protected final Log logger = LogFactory.getLog(getClass());

    

    
    @Autowired
    @Qualifier("platService")
    private IPlateService mplathome ; 
    
    @Autowired
    private IPersonDao mPersonDao;
	/**
	 * Test method for {@link com.myshop.controller.account.AccountController#getCreateForm(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetCreateForm() {
		Plat plat1 = new Plat("choueazezcrotte","Delichieux filet de ","heyehey ");
		
		Plat plat2 = new Plat("cheazeazooo","Delichieux filet de ","heyehey ");
		
		mplathome.persist(plat1);
		mplathome.persist(plat2);
		
	
	}

	/**
	 * Test method for {@link com.myshop.controller.account.AccountController#create(com.myshop.controller.account.Account, org.springframework.validation.BindingResult)}.
	 */
	@Test
	public void testCreate() {
		Person p1 = new Person("Antoine","LAfontaine");
		mPersonDao.persist(p1);
	
	}

	/**
	 * Test method for {@link com.myshop.controller.account.AccountController#getView(java.lang.Long, org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetView() {
		
	}

}
