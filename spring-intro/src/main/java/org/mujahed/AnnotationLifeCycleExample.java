package org.mujahed;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

public class AnnotationLifeCycleExample {

	private static final Logger LOGGER = Logger
			.getLogger(AnnotationLifeCycleExample.class);

	private String dataSource;

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * By default, Spring will not be aware of the @PostConstruct and @PreDestroy
	 * annotation. To enable it, you have to either register
	 * ‘CommonAnnotationBeanPostProcessor‘ or specify the
	 * ‘<context:annotation-config />‘ in bean configuration file.
	 * 
	 * <bean class=
	 * "org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"
	 * /> <context:annotation-config/>
	 */
	@PostConstruct
	public void init() {
		this.dataSource = "syed";
		LOGGER.info("properties has been set $$$$$");
	}
}
