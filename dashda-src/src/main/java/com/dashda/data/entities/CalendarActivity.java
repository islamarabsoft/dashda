/**
 * 
 */
package com.dashda.data.entities;

import java.util.Date;

import com.dashda.enums.CalendarActivityStatusEnum;

/**
 * @author mhanafy
 *
 */
public class CalendarActivity {

	private String event;
	private String specialty;
	private String district;
	private int statusId;
	private String status;
	private Date date;
	private String comment;
	private Integer visitStatusId;
	
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getVisitStatusId() {
		return visitStatusId;
	}
	public void setVisitStatusId(Integer visitStatusId) {
		this.visitStatusId = visitStatusId;
	}
	
	
}
