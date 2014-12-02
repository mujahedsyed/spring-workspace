package com.mujahed.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class PerformanceAspect extends CallTracker {

	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAspect.class);

	@Around("SystemArchitecture.Repository()")
	public void trace(ProceedingJoinPoint proceedingJP) throws Throwable {
		String methodInformation = proceedingJP.getStaticPart().getSignature()
				.toString();
		StopWatch stopWatch = new StopWatch(methodInformation);
		stopWatch.start();
		trackCall();
		try {
			proceedingJP.proceed();
		} finally {
			stopWatch.stop();
			LOGGER.trace(stopWatch.shortSummary());
		}
	}
}
