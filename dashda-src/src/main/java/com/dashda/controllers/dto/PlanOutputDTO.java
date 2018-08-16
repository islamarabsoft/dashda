/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class PlanOutputDTO implements AbstractDTO{

	private int planId;
	private String startDate;
	private String endDate;
	private String subject;
	private String status;
	private String comment;
	
	
	public PlanOutputDTO() {
		super();
	}
	public PlanOutputDTO(int planId, String startDate, String endDate, String subject, String status, String comment) {
		super();
		this.planId = planId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subject = subject;
		this.status = status;
		this.comment = comment;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
