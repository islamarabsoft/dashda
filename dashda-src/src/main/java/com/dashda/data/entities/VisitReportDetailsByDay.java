package com.dashda.data.entities;

import java.sql.Timestamp;

public class VisitReportDetailsByDay {
	
	private Integer visitId;
	private String flmName;
	private Timestamp date;
	
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public String getFlmName() {
		return flmName;
	}
	public void setFlmName(String flmName) {
		this.flmName = flmName;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	private String accountName;
	private String districtName;

}
