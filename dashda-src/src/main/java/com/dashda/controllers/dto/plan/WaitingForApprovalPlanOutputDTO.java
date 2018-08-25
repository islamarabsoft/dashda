/**
 * 
 */
package com.dashda.controllers.dto.plan;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class WaitingForApprovalPlanOutputDTO implements AbstractDTO{

	private int planId;
	private String employeeName;
	private String startDate;
	private String endDate;
	private String subject;
	private String status;
	private int statusId;
	private String comment;
	
	
	public WaitingForApprovalPlanOutputDTO() {
		super();
	}
	public WaitingForApprovalPlanOutputDTO(int planId, String employeeName, String startDate, String endDate, String subject, String status, int statusId, String comment) {
		super();
		this.planId = planId;
		this.employeeName = employeeName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subject = subject;
		this.status = status;
		this.statusId = statusId;
		this.comment = comment;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
