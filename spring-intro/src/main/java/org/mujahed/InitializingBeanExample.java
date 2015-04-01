package org.mujahed;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * Lifecycle example
 *
 */
public class InitializingBeanExample implements InitializingBean {
	private static final Logger LOGGER = Logger
			.getLogger(InitializingBeanExample.class);

	public InitializingBeanExample(){
		LOGGER.info("dataSource from constructor $$$$$ "+dataSource);
	}
	
	private String dataSource;

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public void afterPropertiesSet() throws Exception {
		this.dataSource = "syed";
		LOGGER.info("properties has been set $$$$$");

	}
}
