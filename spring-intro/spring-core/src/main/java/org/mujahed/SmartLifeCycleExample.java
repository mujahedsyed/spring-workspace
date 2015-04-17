package org.mujahed;

import org.apache.log4j.Logger;
import org.springframework.context.SmartLifecycle;

public class SmartLifeCycleExample implements SmartLifecycle{

	private static final Logger LOGGER = Logger.getLogger(SmartLifeCycleExample.class);
	
	private volatile boolean running = false;
	
	public boolean isRunning() {
		return this.running;
	}

	public void start() {
		try{
			this.running = true;
			for(int i=0; i<5; i++){
				LOGGER.info("...");
				Thread.sleep(1000);
			}
			LOGGER.info("We have lift off!");
		} catch(InterruptedException ex){
			throw new RuntimeException(ex);
		}
	}

	public void stop() {
		LOGGER.info("this is one small step for man.. one giant leap for mankind");
	}

	public int getPhase() {
		return 0;
	}

	public boolean isAutoStartup() {
		return false;
	}

	public void stop(Runnable callback) {		
		stop();
		callback.run();
	}

}
