package com.mujahed.aop.simpleaspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAdvice {

	boolean afterCalled = false;
	Logger logger = LoggerFactory.getLogger(TracingAspect.class);

	public void reset() {
		afterCalled = false;
	}

	public boolean isExitingCalled() {
		return afterCalled;
	}

	@After("execution(* *(..))")
	public void exiting(JoinPoint joinPoint) {
		afterCalled = true;
		logger.info("exiting called " + joinPoint.getSignature());
		for (Object arg : joinPoint.getArgs()) {
			logger.info("Arg : " + arg);
		}
	}
}
