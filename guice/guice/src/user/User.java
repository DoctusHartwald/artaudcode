package user;
import mobile.Phone;
import annotation.*;

import com.google.inject.Inject;


public class User {
	private Phone mobile;
	private Phone satellite;

	public Phone getMobile() {
		return mobile;
	}
	@Inject
	public void setMobile(@Mobile Phone mobile) {
	this.mobile = mobile;
	}
	public Phone getSatellite() {
		return satellite;
	}
	@Inject
	public void setSatellite(@Satellite Phone satellite) {
	this.satellite = satellite;
	}
}
