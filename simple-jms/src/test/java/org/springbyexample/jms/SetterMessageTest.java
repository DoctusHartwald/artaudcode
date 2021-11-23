package org.springbyexample.jms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SetterMessageTest {

	final Logger logger = (Logger) LoggerFactory.getLogger(SetterMessageTest.class);

	@Autowired
	private SetterMessage message = null;


	/**
	 * Tests message.
	 */
	@Test
	public void testaffiche() {
		System.out.println("BIDEONNNNNNNNNNNNNNNNN");
		assertNotNull("Constructor message instance is null.", message);

		String msg = message.getMessage();

		assertNotNull("Message is null.", msg);

		String expectedMessage = "Spring is fun.";

		assertEquals("Message should be '" + expectedMessage + "'.", expectedMessage, msg);

		logger.info("message='{}'");
	}

}
