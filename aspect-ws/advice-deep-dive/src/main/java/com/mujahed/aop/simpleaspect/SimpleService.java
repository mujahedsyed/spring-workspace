package com.mujahed.aop.simpleaspect;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {

	public void doSomething(int a) {

	}

	public void throwRuntimeException() {
		try {
			throw new Exception("THis is made up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
