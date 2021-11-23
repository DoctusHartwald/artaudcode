package org.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;


//@Aspect
public class CustomLogger  {
	private static Log LOG = null;
	
	//@Around("execution(public * *(..))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		// start stopwatch
		Object retVal = pjp.proceed();
		
		System.out.println("==========================================================================");
		System.out.println("<ENTREE-ANALYSE>");
		System.out.println("from logging aspect:");
		System.out.println(" execution of ==>entering method [" + pjp.toShortString());
		System.out.println("Signature "+ pjp.getSignature());
		System.out.println("Long : "+pjp.toLongString());
		Object[] argsListeEntry = pjp.getArgs();
		int i  =0;
		for (Object object : argsListeEntry) {
			
			System.out.println("Arguments : "+ i +" <=> " + object);
			i++;
		}
		
		
		
		
		
		System.out.println("------------------------------------------------------------------------------");


		Object point =  pjp.proceed();
		System.out.println("<SORTIE-ANALYSE>");
		System.out.println("<=== from logging aspect: exiting method [" + pjp.toShortString()   + "with return as:" +point);        
		
		
		
		System.out.println("Signature "+ pjp.getSignature());
		System.out.println("Long : "+pjp.toLongString());
		Object[] argsExit = pjp.getArgs();
		int j  =0;
		for (Object object : argsExit) {
			
			System.out.println("Arguments : "+ j +" <=> " + object);
			j++;
		}
		
		return retVal;
	}

	

}
