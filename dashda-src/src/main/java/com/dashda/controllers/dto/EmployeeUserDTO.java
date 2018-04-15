/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class EmployeeUserDTO extends UserDTO {

	private int employeeId;
	@NotEmpty
	private String employeeJobTitle;	
	private String employeeNumber;
	@Digits(fraction = 0, integer = 5)
	private String managerId;
	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the employeeJobTitle
	 */
	public String getEmployeeJobTitle() {
		return employeeJobTitle;
	}
	/**
	 * @param employeeJobTitle the employeeJobTitle to set
	 */
	public void setEmployeeJobTitle(String employeeJobTitle) {
		this.employeeJobTitle = employeeJobTitle;
	}
	/**
	 * @return the employeeNumber
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	/**
	 * @param employeeNumber the employeeNumber to set
	 */
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	/**
	 * @return the managerId
	 */
	public String getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	} 
	
	
}
