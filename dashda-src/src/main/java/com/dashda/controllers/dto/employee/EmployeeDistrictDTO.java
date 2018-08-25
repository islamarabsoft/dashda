/**
 * 
 */
package com.dashda.controllers.dto.employee;

import javax.validation.constraints.NotEmpty;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class EmployeeDistrictDTO implements AbstractDTO {

	private String assignId;
	@NotEmpty
	private String employeeId;
	@NotEmpty
	private String districtId;
	
	
	public String getAssignId() {
		return assignId;
	}
	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	
	
}
