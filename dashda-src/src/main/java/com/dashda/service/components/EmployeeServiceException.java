/**
 * 
 */
package com.dashda.service.components;

import com.dashda.exception.AppExceptionHandler;

/**
 * @author mhanafy
 *
 */
public class EmployeeServiceException extends AppExceptionHandler {

	public EmployeeServiceException() {
		super();
		
	}

	public EmployeeServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public EmployeeServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public EmployeeServiceException(String message) {
		super(message);
		
	}

	public EmployeeServiceException(Throwable cause) {
		super(cause);
		
	}

}
