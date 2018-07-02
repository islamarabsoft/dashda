/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoachingEmployeeSummaryOutputDTO implements AbstractDTO {

	private int employeeId;
	private String employeeName;
	private String dateTime;
	private int count;
	
	
	public ReportCoachingEmployeeSummaryOutputDTO() {
		super();
	}


	public ReportCoachingEmployeeSummaryOutputDTO(int employeeId, String employeeName, String dateTime, int count) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.dateTime = dateTime;
		this.count = count;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	
	
}
