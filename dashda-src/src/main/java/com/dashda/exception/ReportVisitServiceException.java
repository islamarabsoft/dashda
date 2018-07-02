/**
 * 
 */
package com.dashda.exception;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitServiceException extends AppExceptionHandler {

	/**
	 * 
	 */
	public ReportVisitServiceException() {

	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ReportVisitServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public ReportVisitServiceException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public ReportVisitServiceException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public ReportVisitServiceException(Throwable cause) {
		super(cause);

	}

}
