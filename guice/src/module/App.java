package module;
import user.User;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new PhoneModule());
	//	Phone aPhone = injector.getInstance(Phone.class);
	//	aPhone.ring();
		
		User user = injector.getInstance(User.class); 
		user.getMobile().ring();
		user.getSatellite().ring();
	}

}
