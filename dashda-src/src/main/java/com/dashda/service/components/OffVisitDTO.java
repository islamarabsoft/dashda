/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class OffVisitDTO implements AbstractDTO {

	private int offVisitId;
	private int offVisitReasonId;
	private String offVisitReason;
	private String employeeName;
	private String offVisitDate;
	private String status;
	private int statusId;
	
	
	
	public int getOffVisitId() {
		return offVisitId;
	}
	public void setOffVisitId(int offVisitId) {
		this.offVisitId = offVisitId;
	}
	public int getOffVisitReasonId() {
		return offVisitReasonId;
	}
	public void setOffVisitReasonId(int offVisitReasonId) {
		this.offVisitReasonId = offVisitReasonId;
	}
	public String getOffVisitReason() {
		return offVisitReason;
	}
	public void setOffVisitReason(String offVisitReason) {
		this.offVisitReason = offVisitReason;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getOffVisitDate() {
		return offVisitDate;
	}
	public void setOffVisitDate(String offVisitDate) {
		this.offVisitDate = offVisitDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	
}
