/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class AppResponse {
	
	private int status;
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
	public AppResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


}
