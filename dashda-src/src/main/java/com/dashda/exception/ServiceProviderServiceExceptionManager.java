/**
 * 
 */
package com.dashda.exception;

/**
 * @author mhanafy
 *
 */
public class ServiceProviderServiceExceptionManager extends AppExceptionHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ServiceProviderServiceExceptionManager() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ServiceProviderServiceExceptionManager(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceProviderServiceExceptionManager(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ServiceProviderServiceExceptionManager(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceProviderServiceExceptionManager(Throwable cause) {
		super(cause);
	}
	
	
	

	
}
