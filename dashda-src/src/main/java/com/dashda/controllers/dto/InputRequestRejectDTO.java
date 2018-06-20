/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;

/**
 * @author mhanafy
 *
 */
public class InputRequestRejectDTO implements AbstractDTO {

	@Digits(fraction=0, integer=8)
	private int requestId;
	@Digits(fraction=0, integer=8)
	private int requestTypeId;
	
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getRequestTypeId() {
		return requestTypeId;
	}
	public void setRequestTypeId(int requestTypeId) {
		this.requestTypeId = requestTypeId;
	}
	
	
}
