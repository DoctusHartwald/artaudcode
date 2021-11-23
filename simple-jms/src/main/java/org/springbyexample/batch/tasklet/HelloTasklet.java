/**
 * @author artaud
 */
package org.springbyexample.batch.tasklet;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class HelloTasklet implements Tasklet {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	public RepeatStatus execute(StepContribution stepContribution,
			ChunkContext chunkContext) throws Exception {
		System.out.println("Hello world!");
		stepContribution.setExitStatus(ExitStatus.COMPLETED);
		return RepeatStatus.FINISHED;
	}

}
