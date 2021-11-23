package org.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SysoutInterceptor {




	@Around("execution(public * *(..))")
	public Object doLog(ProceedingJoinPoint point) throws Throwable {
		String methodName = point.getTarget().getClass().getSimpleName() + "." + point.getSignature().getName();
		long startTime = System.currentTimeMillis(); // lancement du chrono .


		System.out.println("==> Début méthode : " + methodName);
		System.out.println("==========================================================================");
		System.out.println("<ENTREE-ANALYSE> From logging Spring aspect: ");
		System.out.println("ø execution of ==> method [" + point.toShortString());
		System.out.println("ø Signature : "+ point.getSignature());
		System.out.println("ø Visibilité  : "+ point.getSignature().getModifiers());

		//System.out.println("ø Signature Complet : "+point.toLongString());

		Object[] argsListeEntry = point.getArgs();
		int i  =0;
		for (Object object : argsListeEntry) {
			System.out.println("ø Arguments : "+ i +" <=> " + object);
			i++;
		}


		//***********************************************************

		Object obj = point.proceed();
		System.out.println("<== Fin méthode : " + methodName);
		System.out.println("------------------------------------------------------");
		System.out.println("<SORTIE-ANALYSE> From logging Spring aspect:");
		System.out.println("returning: [" + obj + "]");

		long end = System.currentTimeMillis()  - startTime;
		if(end != 0){
			System.out.println("ø Temps Execution : "+end + " ms ");
		}

		return obj;
	}



}

