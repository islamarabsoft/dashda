/**
 * 
 */
package com.dashda.exception;

/**
 * @author mhanafy
 *
 */
public class OffVisitServiceException extends AppExceptionHandler {

	public OffVisitServiceException() {
		super();
	}

	public OffVisitServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OffVisitServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OffVisitServiceException(String message) {
		super(message);
	}

	public OffVisitServiceException(Throwable cause) {
		super(cause);
	}

}
