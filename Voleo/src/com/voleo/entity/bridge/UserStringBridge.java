package com.voleo.entity.bridge;

import org.hibernate.search.bridge.StringBridge;
import org.hibernate.search.bridge.TwoWayStringBridge;

import com.voleo.entity.user.User;

public class UserStringBridge implements StringBridge, TwoWayStringBridge {

	@Override
	public String objectToString(Object o) {
		if (o == null) {
			return null;
		}
		return ((User)o).getPseudo();
	}

	@Override
	public Object stringToObject(String s) {
		return s;
	}

}
