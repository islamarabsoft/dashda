/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.List;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoachingSummaryInputDTO implements AbstractDTO {

	private List<Integer> employeeIds;
	private String dateFrom;
	private String dateTo;
	
	
	public List<Integer> getEmployeeIds() {
		return employeeIds;
	}
	public void setEmployeeIds(List<Integer> employeeIds) {
		this.employeeIds = employeeIds;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	
	
}
