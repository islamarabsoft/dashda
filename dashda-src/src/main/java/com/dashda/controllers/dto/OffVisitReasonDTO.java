/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class OffVisitReasonDTO implements AbstractDTO {
	
	private int offVisitReasonId;
	private String offVisitreasonName;
	
	
	public int getOffVisitReasonId() {
		return offVisitReasonId;
	}
	public void setOffVisitReasonId(int offVisitReasonId) {
		this.offVisitReasonId = offVisitReasonId;
	}
	public String getOffVisitreasonName() {
		return offVisitreasonName;
	}
	public void setOffVisitreasonName(String offVisitreasonName) {
		this.offVisitreasonName = offVisitreasonName;
	}
	
	

}
