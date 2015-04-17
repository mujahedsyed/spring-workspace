package org.mujahed;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	private static final Logger LOGGER = Logger.getLogger(App.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring.xml");
		InitializingBeanExample ex = (InitializingBeanExample) ctx
				.getBean("app");
		LOGGER.info("dataSource $$$$$ " + ex.getDataSource());

		AnnotationLifeCycleExample annEx = (AnnotationLifeCycleExample) ctx
				.getBean("lifeAnnEx");
		LOGGER.info("AnnotationLifeCycleExample dataSource $$$$$ "
				+ annEx.getDataSource());

		ctx.close();
	}

}
