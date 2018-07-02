/**
 * 
 */
package com.dashda.data.entities;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoachingEmployeeSummary {

	private int employeeId;
	private String employeeName;
	private String dateTime;
	private int count;
	
	
	public ReportCoachingEmployeeSummary() {
		super();
	}
	
	
	public ReportCoachingEmployeeSummary(int employeeId, String employeeName, String dateTime, int count) {
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
