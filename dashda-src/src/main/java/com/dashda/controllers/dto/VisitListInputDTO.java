/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class VisitListInputDTO {

	@NotEmpty
	private String from;
	@NotEmpty
	private String to;
	private int serviceTypeId;
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	
	
}
