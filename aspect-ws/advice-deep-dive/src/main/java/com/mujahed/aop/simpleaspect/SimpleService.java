package com.mujahed.aop.simpleaspect;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {

	public void doSomething(int a) {

	}

	public void throwRuntimeExceptions() {
		try {
			throw new Exception("THis is made up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void throwsException() {
		try {
			throw new Exception("THis is made up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String returnStringAndThrowsRuntimeException() {
		throw new RuntimeException();
	}

	public String returnString() {
		return "mujahed";
	}

	public void throwRuntimeException() {
		try {
			throw new RuntimeException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void throwsRuntimeException() throws Exception {
		throw new RuntimeException("Testing ... ");

	}
}
