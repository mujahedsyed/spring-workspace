package org.mujahed.beantricks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalFarmConfig {

	@Bean
	public Dog dog() {
		return new Dog();
	}

	@Bean
	public Cat cat() {
		return new Cat();
	}

	@Bean
	public MethodTimeLoggingBeanPostProcessor mtPP() {
		return new MethodTimeLoggingBeanPostProcessor();
	}
}
