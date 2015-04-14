package org.mujahed.ch3;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:foo.properties")
public class SpELExample {

	@Autowired
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${data.path}")
	private String dataPath;

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
		System.out.println("data.path " + this.dataPath);
		System.out.println("Env :" + env.getActiveProfiles());
	}
}
