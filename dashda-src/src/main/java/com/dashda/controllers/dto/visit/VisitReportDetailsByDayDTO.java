package com.dashda.controllers.dto.visit;

public class VisitReportDetailsByDayDTO {
	
	private Integer visitId;
	private String flm;
	private String date;
	private String account;
	private String specialty;
	private String district;
	
	
	public VisitReportDetailsByDayDTO(Integer visitId, String flm, String date, String specialty, String account, 
			String district) {
		super();
		this.visitId = visitId;
		this.flm = flm;
		this.date = date;
		this.specialty = specialty;
		this.account = account;
		this.district = district;
	}
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public String getFlm() {
		return flm;
	}
	public void setFlm(String flm) {
		this.flm = flm;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	

}
