/**
 * 
 */
package com.dashda.controllers.dto.plan;

import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.enums.CalendarActivityStatusEnum;

/**
 * @author mhanafy
 *
 */
public class CalendarActivityOutputDTO implements AbstractDTO {

	private String event;
	private String specialty;
	private String district;
	private int statusId;
	private String status;
	private String date;
	private String comment;
	
	
	public CalendarActivityOutputDTO() {
		super();
	}
	
	public CalendarActivityOutputDTO(String event, String specialty, String district, int statusId,
			String status, String date, String comment) {
		super();
		this.event = event;
		this.specialty = specialty;
		this.district = district;
		this.statusId = statusId;
		this.status = status;
		this.date = date;
		this.comment = comment;
	}
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
