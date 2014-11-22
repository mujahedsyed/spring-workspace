package com.mujahed.aop.simpleaspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundAdvice {

	private Logger logger = LoggerFactory.getLogger(AroundAdvice.class);

	private boolean called;

	public void reset() {
		called = false;
	}

	@Around(value = "execution(* *(..))")
	public Object trace(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {
		String methodInformation = proceedingJoinPoint.getStaticPart()
				.getSignature().toString();
		logger.info("Entering :" + methodInformation);
		called = true;

		try {
			return proceedingJoinPoint.proceed();
		} catch (Throwable ex) {
			logger.error("exception in " + methodInformation, ex);
			throw ex;
		} finally {
			logger.info("Exiting :" + methodInformation);
		}
	}

	public boolean isCalled() {
		return called;
	}
}
