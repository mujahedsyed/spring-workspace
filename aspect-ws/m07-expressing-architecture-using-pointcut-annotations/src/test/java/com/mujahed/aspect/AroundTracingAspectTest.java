package com.mujahed.aspect;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mujahed.configuration.SystemConfiguration;
import com.mujahed.repository.SimpleRepository;
import com.mujahed.service.SimpleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfiguration.class)
public class AroundTracingAspectTest {

	@Autowired
	TracingAspect tracingAspect;

	@Autowired
	SimpleRepository myRepository;

	@Autowired
	SimpleService myService;
	
	@org.junit.Before
	public void setUp() {
		tracingAspect.resetCalled();
	}

	@Test
	public void tracingIsCalledForRepositories() {
		assertFalse(tracingAspect.isCalled());
		myRepository.doSomething();
		assertTrue(tracingAspect.isCalled());
	}

	@Test
	public void tracingIsCalledForServices() {
		assertFalse(tracingAspect.isCalled());
		myService.doSomething();
		assertTrue(tracingAspect.isCalled());
	}
}
