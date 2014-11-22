package com.mujahed.aop.simpleaspect;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mujahed.aop.configuration.AdviceDeepDiveConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdviceDeepDiveConfiguration.class)
public class AfterThrowingAdviceTest {

	@Autowired
	SimpleService simpleService;

	@Autowired
	AfterThrowingAdvice afterThrowingAdvice;

	@Before
	public void reset() {
		afterThrowingAdvice.reset();
	}

	@Test
	public void afterThrowingIsNotCalledIfMethodReturnsSuccessfully() {
		assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
		simpleService.throwsException();
		assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
	}

	@Test(expected = RuntimeException.class)
	public void afterThrowingIsCalledIfMethodThrowsRuntimeException()
			throws Exception {
		assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
		try {
			simpleService.throwsRuntimeException();
		} finally {
			assertTrue(afterThrowingAdvice.isAfterThrowingCalled());
		}
	}
}
