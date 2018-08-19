/**
 * 
 */
package com.dashda.controllers.dto;

import org.springframework.http.HttpStatus;

/**
 * @author mhanafy
 *
 */
public class AppResponse {
	
	private HttpStatus status;
	private String message;
	
	
	/**
	 * 
	 */
	public AppResponse() {
		super();
	}


	/**
	 * @param status
	 * @param message
	 */
	public AppResponse(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


}
