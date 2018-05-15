/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;

/**
 * @author mhanafy
 *
 */
public class InputRequestApprovalDTO implements AbstractDTO {

	@Digits(fraction=0, integer=8)
	private int requestId;
	@Digits(fraction=0, integer=8)
	private int requestTypeId;
	private int doubleVisit;
	
	
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
	public int getDoubleVisit() {
		return doubleVisit;
	}
	public void setDoubleVisit(int doubleVisit) {
		this.doubleVisit = doubleVisit;
	}
	
	
}
