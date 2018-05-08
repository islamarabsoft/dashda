/**
 * 
 */
package com.dashda.controllers.dto;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * @author mhanafy
 *
 */
public class EmployeeDoctorDTO implements AbstractDTO {

	@Digits(integer=6, fraction=0)
	@Positive
	private int assignedId;
	@Digits(integer=6, fraction=0)
	private int serviceProviderId;
	private int employeeId;
	
	
	public int getAssignedId() {
		return assignedId;
	}
	public void setAssignedId(int assignedId) {
		this.assignedId = assignedId;
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
	
	
}
