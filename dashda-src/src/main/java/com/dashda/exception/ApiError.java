/**
 * 
 */
package com.dashda.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author mhanafy
 *
 */
public class ApiError {

	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   private String debugMessage;
	   private String arErrorMessage;
	   private String enErrorMessage;
	   private String errorCode;

	   public ApiError() {
	       timestamp = LocalDateTime.now();
	   }

	   ApiError(HttpStatus status) {
	       this();
	   }

	   ApiError(HttpStatus status, Throwable ex) {
	       this();
	       this.message = "Unexpected error";
	       this.debugMessage = ex.getLocalizedMessage();
	   }

	   ApiError(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }


	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
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
