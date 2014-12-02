package com.mujahed.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLoggerAspect extends CallTracker {

	Logger LOGGER = LoggerFactory.getLogger(ExceptionLoggerAspect.class);

	@AfterThrowing(pointcut = "SystemArchitecture.Repository() || SystemArchitecture.Service()", throwing = "ex")
	public void logException(Exception ex) {
		trackCall();
		LOGGER.error("Exception ", ex);
	}
}
