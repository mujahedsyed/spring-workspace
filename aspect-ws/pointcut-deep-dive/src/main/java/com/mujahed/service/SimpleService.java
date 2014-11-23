package com.mujahed.service;

import org.springframework.stereotype.Service;

import annotation.Trace;

@Service
public class SimpleService {

	public void doSomething() {
		// TODO Auto-generated method stub

	}

	@Trace
	public void annotated() {
	}

}
