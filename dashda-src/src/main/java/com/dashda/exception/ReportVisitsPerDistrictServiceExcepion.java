/**
 * 
 */
package com.dashda.exception;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitsPerDistrictServiceExcepion extends AppExceptionHandler {

	/**
	 * 
	 */
	public ReportVisitsPerDistrictServiceExcepion() {

	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ReportVisitsPerDistrictServiceExcepion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public ReportVisitsPerDistrictServiceExcepion(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public ReportVisitsPerDistrictServiceExcepion(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public ReportVisitsPerDistrictServiceExcepion(Throwable cause) {
		super(cause);

	}

}
