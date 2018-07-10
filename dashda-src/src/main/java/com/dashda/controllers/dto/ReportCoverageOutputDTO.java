/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportCoverageOutputDTO implements AbstractDTO {
	
	private int taget;
	private int Actual;
	private String percentage;
	
	
	public ReportCoverageOutputDTO(int taget, int actual, String percentage) {
		super();
		this.taget = taget;
		this.Actual = actual;
		this.percentage = percentage;
	}
	
	
	public ReportCoverageOutputDTO() {
		super();
	}


	public int getTaget() {
		return taget;
	}
	public void setTaget(int taget) {
		this.taget = taget;
	}
	public int getActual() {
		return Actual;
	}
	public void setActual(int actual) {
		Actual = actual;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	
	
	

}
