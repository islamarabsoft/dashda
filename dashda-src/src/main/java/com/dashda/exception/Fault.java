/**
 * 
 */
package com.dashda.exception;

/**
 * @author mhanafy
 *
 */
public class Fault {

	private String errorCode;
	private String arErrorMessage;
	private String enErrorMessage;
	
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
