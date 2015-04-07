package org.mujahed.beantricks;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				AnimalFarmConfig.class);

		Cat cat = ctx.getBean(Cat.class);
		cat.meow();

		Dog dog = ctx.getBean(Dog.class);
		dog.bark();
		ctx.close();
	}

}
