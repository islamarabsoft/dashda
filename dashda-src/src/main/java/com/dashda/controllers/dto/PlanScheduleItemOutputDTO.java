/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class PlanScheduleItemOutputDTO implements AbstractDTO {

	private int id;
	private int serviceProviderId;
	private int employeeId;
	private String date;
	
	
	public PlanScheduleItemOutputDTO() {
		super();
	}


	public PlanScheduleItemOutputDTO(int id, int serviceProviderId, int employeeId, String date) {
		super();
		this.id = id;
		this.serviceProviderId = serviceProviderId;
		this.employeeId = employeeId;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getServiceProviderId() {
		return serviceProviderId;
	}


	public void setServiceProviderId(int serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	
}
