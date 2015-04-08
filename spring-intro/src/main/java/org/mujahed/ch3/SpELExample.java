package org.mujahed.ch3;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpELExample {

	@Value("#{ T(Math).random() }")
	private double aRandomValue;

	@Value("#{systemProperties['user.home']}")
	private String userHome;

	private File tmpDir;

	@Value("#{systemProperties['java.io.tmpdir']}")
	public void setIoTmpDir(String tmpDir) {
		this.tmpDir = new File(tmpDir);
	}

	@PostConstruct
	public void print() {
		System.out.println("User Home " + userHome);
		System.out.println("A Random Value " + aRandomValue);
		System.out.println("IO Temp dir " + this.tmpDir.getAbsolutePath());	
	}
}
