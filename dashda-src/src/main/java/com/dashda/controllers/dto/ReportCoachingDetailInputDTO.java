/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoachingDetailInputDTO implements AbstractDTO {

	private int employeeId;
	private String dateTime;
	
	public ReportCoachingDetailInputDTO() {
		super();
	}

	public ReportCoachingDetailInputDTO(int employeeId, String dateTime) {
		super();
		this.employeeId = employeeId;
		this.dateTime = dateTime;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
	
}
