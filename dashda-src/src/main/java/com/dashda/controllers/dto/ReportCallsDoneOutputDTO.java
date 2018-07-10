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
	private String percentage;
	
	
	public ReportCallsDoneOutputDTO() {
		super();
	}
	
	public ReportCallsDoneOutputDTO(int tagetVisits, int actualVisits, String percentage) {
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
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	
	
}
