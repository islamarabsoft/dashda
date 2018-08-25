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
public class ReportVisitsPerSpecialtyInputDTO implements AbstractDTO {

	@NotEmpty
	private String dateFrom;
	@NotEmpty
	private String dateTo;
	
	
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
