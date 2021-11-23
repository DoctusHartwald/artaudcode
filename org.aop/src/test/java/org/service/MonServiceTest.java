/**
 * 
 */
package org.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author artaud
 *
 */
@ContextConfiguration(locations = "classpath:springContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MonServiceTest {

	@Autowired
	private MonService vMonService;
	/**
	 * Test method for {@link org.service.MonService#hello(java.lang.String)}.
	 */
	@Test
	public void testHello() {
		
		//ApplicationContext context = new ClassPathXmlApplicationContext( new String[] {"springContext.xml"} );
		//MonService monService = (MonService) context.getBean("monService");
		//monService.hello("from Spring !");
		String titit = vMonService.hello("this is a hello from Spring AOP Frmaework ");
		
		
		String toto = vMonService.hello("Hello from Antoine !!! ");

	}

}
