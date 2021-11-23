import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.StringMessage;


public class Bootstrap {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		
		Gateway gateway = (Gateway) context.getBean("gateway");
	    gateway.send("Spring Integration rocks");

		
		//MessageChannel input = (MessageChannel) context.getBean("input");
		//input.send(new StringMessage("Spring Integration rocks"));
		
		//PollableChannel output = (PollableChannel) context.getBean("output");
		//Message<?> reply = output.receive();
		//System.out.println("received: " + reply);

	}
}
