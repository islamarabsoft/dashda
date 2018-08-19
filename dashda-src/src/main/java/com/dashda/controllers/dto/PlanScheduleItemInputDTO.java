/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author mohamed.hanfy
 *
 */
public class PlanScheduleItemInputDTO implements AbstractDTO {

	@NotEmpty
	private String date;
	@NotEmpty
	private int[] serviceProviderIds;
	private int planId;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int[] getServiceProviderIds() {
		return serviceProviderIds;
	}
	public void setServiceProviderIds(int[] serviceProviderIds) {
		this.serviceProviderIds = serviceProviderIds;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	
	
}
