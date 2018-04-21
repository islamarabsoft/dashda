/**
 * 
 */
package com.dashda.exception;

/**
 * @author mhanafy
 *
 */
public class DashdaExceptionHandling extends Exception {

	private String arErrorMessage = "AR"; 
	private String enErrorMessage = "EN";
	private String errorCode = "001";

	/**
	 * 
	 */
	public DashdaExceptionHandling() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DashdaExceptionHandling(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DashdaExceptionHandling(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DashdaExceptionHandling(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DashdaExceptionHandling(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getArErrorMessage() {
		return arErrorMessage;
	}

	public void setArErrorMessage(String arErrorMessage) {
		this.arErrorMessage = arErrorMessage;
	}

	public String getEnErrorMessage() {
		return enErrorMessage;
	}

	public void setEnErrorMessage(String enErrorMessage) {
		this.enErrorMessage = enErrorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
