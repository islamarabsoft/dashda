/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCallsDoneOutputDTO implements AbstractDTO {

	private int tagetVisits;
	private int ActualVisits;
	private int percentage;
	
	
	public ReportCallsDoneOutputDTO() {
		super();
	}
	
	public ReportCallsDoneOutputDTO(int tagetVisits, int actualVisits, int percentage) {
		super();
		this.tagetVisits = tagetVisits;
		this.ActualVisits = actualVisits;
		this.percentage = percentage;
	}
	public int getTagetVisits() {
		return tagetVisits;
	}
	public void setTagetVisits(int tagetVisits) {
		this.tagetVisits = tagetVisits;
	}
	public int getActualVisits() {
		return ActualVisits;
	}
	public void setActualVisits(int actualVisits) {
		ActualVisits = actualVisits;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	
	
}
