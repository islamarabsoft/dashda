package com.dashda.exception;

public class VisitReportException extends AppExceptionHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6868717791313339L;

	public VisitReportException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public VisitReportException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public VisitReportException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public VisitReportException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public VisitReportException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
