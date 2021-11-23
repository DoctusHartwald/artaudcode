package rewards.ui;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import rewards.Dining;


/**
 * A command line interface for entering {@link Dining} events.
 */
public class DiningEntryUI {

	/**
	 * Bootstrap a new {@link DiningEntryUI} from the command line.
	 * @param args no arguments expected
	 */
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context =
			new ClassPathXmlApplicationContext("rewards/ui/diningentry-config.xml");

		context.start();
	}

}
