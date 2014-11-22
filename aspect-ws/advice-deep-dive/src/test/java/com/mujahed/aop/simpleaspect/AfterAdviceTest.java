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
public class AfterAdviceTest {

	@Autowired
	AfterAdvice afterAdvice;

	@Autowired
	SimpleService simpleService;

	@Before
	public void reset() {
		afterAdvice.reset();
	}

	@Test
	public void afterAspectIsCalledIfMethodReturnsSuccessfully() {
		assertFalse(afterAdvice.isExitingCalled());
		simpleService.doSomething(12);
		assertTrue(afterAdvice.isExitingCalled());
	}

	@Test
	public void afterAspectIsCalledIfMethodThrowsException() {
		assertFalse(afterAdvice.isExitingCalled());
		try {
			simpleService.throwRuntimeException();
		} finally {
			assertTrue(afterAdvice.isExitingCalled());
		}
		simpleService.doSomething(34);
		assertTrue(afterAdvice.isExitingCalled());
	}
}
