package com.myshop.workflow.order;

import org.jbpm.pvm.Execution;
import org.jbpm.pvm.ProcessDefinition;
import org.jbpm.pvm.ProcessFactory;

public class OrderWorkflowDefinition {

	public static void main(String[] args) {
		ProcessDefinition processDefinition = ProcessFactory.build()
			.node("a").initial().behaviour(new Display("hello"))
			.transition().to("b")
			.node("b").behaviour(new Display("world"))
		.done();

		Execution exec = processDefinition.startExecution();
	}
}
