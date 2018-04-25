/**
 * 
 */
package com.dashda.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.dashda.utilities.Messages;
import com.dashda.utilities.SpringContext;

/**
 * @author mhanafy
 *
 */
@ControllerAdvice
public class AppExceptionHandler extends Exception {

	
	private String arErrorMessage; 
	private String enErrorMessage;
	private String errorCode;
	private String message;
	/**
	 * 
	 */
	public AppExceptionHandler() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AppExceptionHandler(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AppExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AppExceptionHandler(String message) {
		super(message); 
		Messages messages = (Messages)SpringContext.getApplicationContext().getBean("messages");
		this.message = message + " -- " + messages.get(message, Locale.ENGLISH);
		this.errorCode = message;
		this.arErrorMessage = messages.get(message, new Locale("ar"));
		this.enErrorMessage = messages.get(message, Locale.ENGLISH);
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	/**
	 * @param cause
	 */
	public AppExceptionHandler(Throwable cause) {
		super(cause);
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
