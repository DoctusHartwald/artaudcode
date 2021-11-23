/**
 * 
 */
package com.objis.springmvcdemo.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.objis.springmvcdemo.domaine.Plat;
import com.objis.springmvcdemo.service.IPlatService;

/**
 * @author artaud
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:/war/WEB-INF/objisapp-data.xml","classpath*:/war/WEB-INF/objisapp-service.xml"})
public class PlatHomeTest  {


	@Autowired
	@Qualifier("platService")
	IPlatService vPlatService ; 


	//	
	Plat mPlat = new Plat();
	ApplicationContext context;

	@Before
	public void initialer(){
		int id = 3 ; 
		mPlat.setDescription("Lapin à la moutarde et purée ");
		mPlat.setNomPlat("Lapin");
		mPlat.setType("sans épice");
		mPlat.setId(id);

		//context = new ClassPathXmlApplicationContext(new String[] { "classpath*:/war/WEB-INF/objisapp-data.xml","classpath*:/war/WEB-INF/objisapp-service.xml"});

	}
	/**
	 * Test method for {@link com.objis.springmvcdemo.dao.PlatHome#setEntityManagerFactory(javax.persistence.EntityManagerFactory)}.
	 * @author artaud
	 * 
	 */
	@Test
	public void testSetEntityManagerFactory() {
	}

	/**
	 * Test method for {@link com.objis.springmvcdemo.dao.PlatHome#persist(com.objis.springmvcdemo.domaine.Plat)}.
	 * @author artaud
	 */
	@Test
	public void testPersist() {
		//PlatHome vPlatHome = (PlatHome) context.getBean("platHome");
		//vPlatHome.persist(mPlat);
	}

	/**
	 * Test method for {@link com.objis.springmvcdemo.dao.PlatHome#remove(com.objis.springmvcdemo.domaine.Plat)}.
	 */
	@Test
	public void testRemove() {

	}

	/**
	 * Test method for {@link com.objis.springmvcdemo.dao.PlatHome#merge(com.objis.springmvcdemo.domaine.Plat)}.
	 */
	@Test
	public void testMerge() {

	}

	/**
	 * Test method for {@link com.objis.springmvcdemo.dao.PlatHome#findById(java.lang.Integer)}.
	 */
	@Test
	public void testFindByIdInteger() {

	}

	/**
	 * Test method for {@link com.objis.springmvcdemo.dao.PlatHome#findById(int)}.
	 */
	@Test
	public void testFindByIdInt() {

	}

}
