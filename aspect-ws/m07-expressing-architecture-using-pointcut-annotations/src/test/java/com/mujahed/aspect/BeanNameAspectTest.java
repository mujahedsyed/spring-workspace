package com.mujahed.aspect;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mujahed.configuration.PointCutDeepDiveConfiguration;
import com.mujahed.repository.SimpleRepository;
import com.mujahed.service.SimpleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PointCutDeepDiveConfiguration.class)
public class BeanNameAspectTest {

	@Autowired
	BeanNameAspect beanNameAspect;

	@Autowired
	SimpleService simpleService;

	@Autowired
	SimpleRepository simpleRepository;

	@Before
	public void setup() {
		beanNameAspect.resetCalled();
	}

	@Test
	public void tracingOnServiceIsCalled(){
		assertThat(beanNameAspect.getCalled(), is(0));
		simpleService.doSomething();
		assertThat(beanNameAspect.getCalled(), is(1));
	}
	
	@Test
	public void tracingOnRepositoryIsNotCalled(){
		assertThat(beanNameAspect.getCalled(), is(0));
		simpleRepository.doSomething();
		assertThat(beanNameAspect.getCalled(), is(0));
	}
	
}
