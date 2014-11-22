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
public class AroundAdviceTest {

	@Autowired
	SimpleService simpleService;

	@Autowired
	AroundAdvice aroundAdvice;

	@Before
	public void reset() {
		aroundAdvice.reset();
	}

	@Test
	public void aroundAdviceIsCalledForSimpleMethod() {
		assertFalse(aroundAdvice.isCalled());
		simpleService.doSomething(12);
		assertTrue(aroundAdvice.isCalled());
	}

	@Test(expected = RuntimeException.class)
	public void aroundAdviceIsCalledWhenExceptionIsThrown() throws Exception {
		assertFalse(aroundAdvice.isCalled());
		try {
			simpleService.throwsRuntimeException();
		} finally {
			assertTrue(aroundAdvice.isCalled());
		}
	}

	@Test
	public void aroundAdviceIsCalledIfValueIsReturned() {
		assertFalse(aroundAdvice.isCalled());
		simpleService.returnString();
		assertTrue(aroundAdvice.isCalled());
	}
}
