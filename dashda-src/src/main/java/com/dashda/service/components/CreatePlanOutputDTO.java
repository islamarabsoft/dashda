/**
 * 
 */
package com.dashda.service.components;

import java.util.Date;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class CreatePlanOutputDTO implements AbstractDTO {

	private int id;
	private String startDate;
	private String endDate;
	
	
	public CreatePlanOutputDTO(int id, String startDate, String endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	


}
