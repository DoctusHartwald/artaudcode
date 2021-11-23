package module;

import mobile.MobilePhone;
import mobile.Phone;
import mobile.SatellitePhone;

import com.google.inject.AbstractModule;
import annotation.*;

public class PhoneModule extends AbstractModule {

	@Override
	public void configure() {
		bind(Phone.class).annotatedWith(Mobile.class).to(MobilePhone.class);
		bind(Phone.class).annotatedWith(Satellite.class).to(SatellitePhone.class);
	}
}

