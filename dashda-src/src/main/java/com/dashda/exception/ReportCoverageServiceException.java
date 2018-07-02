/**
 * 
 */
package com.dashda.exception;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoverageServiceException extends AppExceptionHandler {

	public ReportCoverageServiceException() {
		super();
		
	}

	public ReportCoverageServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public ReportCoverageServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ReportCoverageServiceException(String message) {
		super(message);
		
	}

	public ReportCoverageServiceException(Throwable cause) {
		super(cause);
		
	}

}
