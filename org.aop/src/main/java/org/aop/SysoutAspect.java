package org.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SysoutAspect {
	
	
  @Pointcut("execution(public * *(..))")
  public void logPointcut(){};
}

