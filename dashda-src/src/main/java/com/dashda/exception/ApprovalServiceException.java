package com.dashda.exception;

public class ApprovalServiceException extends AppExceptionHandler {

	public ApprovalServiceException() {
		super();
	}

	public ApprovalServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ApprovalServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApprovalServiceException(String message) {
		super(message);
	}

	public ApprovalServiceException(Throwable cause) {
		super(cause);
	}

}
