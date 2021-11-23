package message;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)


@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessage {


	@Autowired
	DirectChannel  input ; 

	@Autowired
	IMessageExchanger exchanger; 

	@Autowired
	PollableChannel inputExterieur; 

	@Test
	public void testSeeder(){

		List<Message<?>> messages = new ArrayList<Message<?>>();
		String message ="Sending message No :";
		//create a Message with a new payload
		for(int i=0;i<10;i++){
			exchanger.messageExpose("mon message");
			input.send(MessageBuilder.withPayload(message+" "+i).build());
			Message<?> T = inputExterieur.receive();

			//System.out.println("=>>" + T.toString());

			if(T!=null)
				messages.add(T);
		}

		//test if we received our 10 messages .
		assertThat(messages.size(), is(10));
	}


}
