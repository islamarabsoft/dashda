/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.List;

/**
 * @author mhanafy
 *
 */
public class EmployeeSchedulesDTO implements AbstractDTO{

	private String employeeId;
	private String employeeName;
	private List<ScheduleDTO> schedules;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public List<ScheduleDTO> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScheduleDTO> schedules) {
		this.schedules = schedules;
	}


	
	
	
}
