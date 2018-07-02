/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportSummaryOutputDTO implements AbstractDTO {

	private int count;
	private int employeeId;
	private String employeeName;
	
	
	public ReportSummaryOutputDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportSummaryOutputDTO(int count, int employeeId, String employeeName) {
		super();
		this.count = count;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
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
	
	
}
