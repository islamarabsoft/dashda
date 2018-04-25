/**
 * 
 */
package com.dashda.controllers.dto;


import javax.validation.constraints.Digits;

/**
 * @author mhanafy
 *
 */
public class EmployeeDoctorDTO implements AbstractDTO {

	@Digits(integer=6, fraction=0)
	private int assignedId;
	@Digits(integer=6, fraction=0)
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
