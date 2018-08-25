/**
 * 
 */
package com.dashda.controllers.dto.report;

import javax.validation.constraints.NotEmpty;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitsPerSpecialtyDetailsInputDTO implements AbstractDTO {

	private int specialtyId;
	@NotEmpty
	private String dateFrom;
	@NotEmpty
	private String dateTo;
	
	
	public int getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
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
