package org.mujahed.beantricks;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MethodTimeLoggingAspect {

	@Around("@annotation(org.mujahed.beantricks.Timed)")
	public Object time(ProceedingJoinPoint invocation) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("----------------------------------------");
		System.out.println(invocation.getSignature().getName() + "()");
		Object result = invocation.proceed(); // call the method
		long stop = System.currentTimeMillis();
		System.out.println("time: " + (stop - start));
		return result;

	}

}
