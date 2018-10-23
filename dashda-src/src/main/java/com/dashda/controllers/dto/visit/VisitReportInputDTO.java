package com.dashda.controllers.dto.visit;

import com.dashda.controllers.dto.AbstractDTO;

public class VisitReportInputDTO implements AbstractDTO {
	
	private int regional=0;
	private int flm=0;
	private int mp=0;
	private int product=0;
	private int productline=0;
	private int specialty=0;
	private String datefrom=null;
	private String dateto=null;
	
	private String specificDate=null;
	
	public String getDatefrom() {
		return datefrom;
	}
	public String getSpecificDate() {
		return specificDate;
	}
	public void setSpecificDate(String specificDate) {
		this.specificDate = specificDate;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateto() {
		return dateto;
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	private int ampm=0;
	
	
	
	public int getRegional() {
		return regional;
	}
	public void setRegional(int regional) {
		this.regional = regional;
	}
	public int getFlm() {
		return flm;
	}
	public void setFlm(int flm) {
		this.flm = flm;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getProductline() {
		return productline;
	}
	public void setProductline(int productline) {
		this.productline = productline;
	}
	public int getSpecialty() {
		return specialty;
	}
	public void setSpecialty(int specialty) {
		this.specialty = specialty;
	}

	public int getAmpm() {
		return ampm;
	}
	public void setAmpm(int ampm) {
		this.ampm = ampm;
	}
	
}
