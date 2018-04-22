/**
 * 
 */
package com.dashda.controllers;


import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class EmployeeDoctorDTO implements AbstractDTO {

	private int assignedId;
	private int doctorId;
	private int employeeId;
	
	
	public int getAssignedId() {
		return assignedId;
	}
	public void setAssignedId(int assignedId) {
		this.assignedId = assignedId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
