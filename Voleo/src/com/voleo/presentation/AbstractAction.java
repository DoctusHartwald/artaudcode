package com.voleo.presentation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction extends ActionSupport {

	protected Long getUserIdInSession() {
		return (Long)ActionContext.getContext().getSession().get("userId");
	}

}
