/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class AddOffVisitOutputDTO implements AbstractDTO {

	private int offVisitId;
	private int reasonId;
	private String date;
	
	
	public int getOffVisitId() {
		return offVisitId;
	}
	public void setOffVisitId(int offVisitId) {
		this.offVisitId = offVisitId;
	}
	public int getReasonId() {
		return reasonId;
	}
	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
