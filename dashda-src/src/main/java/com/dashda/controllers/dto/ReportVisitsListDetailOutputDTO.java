/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportVisitsListDetailOutputDTO implements AbstractDTO {

	private int visitId;
	private String firstLineManager;
	private String dateTime;
	private String accountName;
	private String specialty;
	private String brick;
	
	
	public ReportVisitsListDetailOutputDTO() {
		super();
	}
	
	public ReportVisitsListDetailOutputDTO(int visitId, String firstLineManager, String dateTime, String accountName, String specialty,
			String brick) {
		super();
		this.visitId = visitId;
		this.firstLineManager = firstLineManager;
		this.dateTime = dateTime;
		this.accountName = accountName;
		this.specialty = specialty;
		this.brick = brick;
	}
	
	
	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public String getFirstLineManager() {
		return firstLineManager;
	}
	public void setFirstLineManager(String firstLineManager) {
		this.firstLineManager = firstLineManager;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getBrick() {
		return brick;
	}
	public void setBrick(String brick) {
		this.brick = brick;
	}
	
	
	
}
