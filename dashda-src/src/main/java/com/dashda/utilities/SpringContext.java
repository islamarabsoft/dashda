/**
 * 
 */
package com.dashda.utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author mhanafy
 *
 */
public class SpringContext implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
	    return applicationContext;
	}
}
