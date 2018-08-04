/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitsPerFLMDetailsInputDTO {
	
	
	private int flmId;
	@NotEmpty
	private String dateFrom;
	@NotEmpty
	private String dateTo;
	

	public int getFlmId() {
		return flmId;
	}

	public void setFlmId(int flmId) {
		this.flmId = flmId;
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
