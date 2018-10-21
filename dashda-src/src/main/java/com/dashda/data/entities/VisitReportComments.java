package com.dashda.data.entities;

public class VisitReportComments {
	
	private String mr;
	private String district;
	private String product;
	private String specialty;
	private String comment;
	
	
	public VisitReportComments(String mr, String district, String product, String specialty, String comment) {
		super();
		this.mr = mr;
		this.district = district;
		this.product = product;
		this.specialty = specialty;
		this.comment = comment;
	}
	public VisitReportComments() {
		// TODO Auto-generated constructor stub
	}
	public String getMr() {
		return mr;
	}
	public void setMr(String mr) {
		this.mr = mr;
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
