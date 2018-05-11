/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class UpdateOffVisitInputDTO implements AbstractDTO {
	
	@Digits(fraction=0, integer=8)
	private int offVisitId;
	@Digits(fraction=0, integer=8)
	private int reasonId;
	@NotEmpty
	private String date;
	private String comment;
	
	
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
