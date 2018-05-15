/**
 * 
 */
package com.dashda.controllers;

import javax.validation.constraints.Digits;

import com.dashda.controllers.dto.AbstractDTO;

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
