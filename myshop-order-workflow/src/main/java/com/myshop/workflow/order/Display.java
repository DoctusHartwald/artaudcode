package com.myshop.workflow.order;

import org.jbpm.pvm.Activity;
import org.jbpm.pvm.Execution;

public class Display implements Activity {

	private static final long serialVersionUID = 1L;

	String message;



	public Display(String message) {

		this.message = message;

	}

	public void execute(Execution arg0) throws Exception {
		System.out.println(message);

	}

}