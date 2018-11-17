package com.dashda.controllers.dto.visit;

public class VisitReportCommnetsDTO {
	private String mr;
	private String account;
	private String date;
	private String district;
	private String product;
	private String specialty;
	private String comment;
	
	
	public VisitReportCommnetsDTO(String mr, String account, String date, String district, String product, String specialty, String comment) {
		super();
		this.mr = mr;
		this.district = district;
		this.product = product;
		this.specialty = specialty;
		this.comment = comment;
		this.account = account;
		this.date = date;
	}
	
	public VisitReportCommnetsDTO() {
	}
	
	public String getMr() {
		return mr;
	}
	public void setMr(String mr) {
		this.mr = mr;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
