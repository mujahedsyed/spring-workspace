package com.mujahed.aop.simpleaspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterReturningAdvice {

	Logger logger = LoggerFactory.getLogger(AfterReturningAdvice.class);

	private boolean afterReturnCalled = false;

	public void reset() {
		afterReturnCalled = false;
	}

	public boolean isAfterReturningCalled() {
		return afterReturnCalled;
	}

	@AfterReturning(pointcut = "execution(* *(..))", returning = "string")
	public void logResult(String string) {
		afterReturnCalled = true;
		logger.info("result :" + string);
	}

}
