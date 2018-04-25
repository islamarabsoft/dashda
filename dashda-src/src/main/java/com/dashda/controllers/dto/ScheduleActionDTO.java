/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.List;

/**
 * @author mhanafy
 *
 */
public class ScheduleActionDTO implements AbstractDTO {
	private List<Integer> scheduleDTOs;
	private String dubleVisit;
	
	public List<Integer> getScheduleDTOs() {
		return scheduleDTOs;
	}
	public void setScheduleDTOs(List<Integer> scheduleDTOs) {
		this.scheduleDTOs = scheduleDTOs;
	}
	public String getDubleVisit() {
		return dubleVisit;
	}
	public void setDubleVisit(String dubleVisit) {
		this.dubleVisit = dubleVisit;
	}
	
	

}
