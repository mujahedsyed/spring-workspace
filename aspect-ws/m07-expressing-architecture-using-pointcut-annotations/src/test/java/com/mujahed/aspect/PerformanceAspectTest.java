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
public class PerformanceAspectTest {

	@Autowired
	PerformanceAspect performanceAspect;

	@Autowired
	SimpleRepository myRepository;

	@Autowired
	SimpleService myService;
	
	@org.junit.Before
	public void setUp() {
		performanceAspect.resetCalled();
	}

	@Test
	public void tracingIsCalledForRepositories() {
		assertFalse(performanceAspect.isCalled());
		myRepository.doSomething();
		assertTrue(performanceAspect.isCalled());
	}

	@Test
	public void tracingIsNotCalledForServices() {
		assertFalse(performanceAspect.isCalled());
		myService.doSomething();
		assertFalse(performanceAspect.isCalled());
	}
}
