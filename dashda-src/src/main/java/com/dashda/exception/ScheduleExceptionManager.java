/**
 * 
 */
package com.dashda.exception;

/**
 * @author mhanafy
 *
 */
public class ScheduleExceptionManager extends AppExceptionHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ScheduleExceptionManager(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public ScheduleExceptionManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ScheduleExceptionManager(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ScheduleExceptionManager(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ScheduleExceptionManager(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
