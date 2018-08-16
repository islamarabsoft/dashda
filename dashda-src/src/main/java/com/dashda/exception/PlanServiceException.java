/**
 * 
 */
package com.dashda.exception;

/**
 * @author mohamed.hanfy
 *
 */
public class PlanServiceException extends AppExceptionHandler {

	/**
	 * 
	 */
	public PlanServiceException() {

	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PlanServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public PlanServiceException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public PlanServiceException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public PlanServiceException(Throwable cause) {
		super(cause);

	}

}
