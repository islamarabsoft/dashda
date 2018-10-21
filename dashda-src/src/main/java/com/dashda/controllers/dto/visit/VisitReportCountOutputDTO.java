package com.dashda.controllers.dto.visit;

import java.math.BigInteger;

public class VisitReportCountOutputDTO {
	
	 public VisitReportCountOutputDTO(Integer productlineid, String productline, String regional, Integer regionalid, String flm,
			Integer flmid, String mp, Integer mpid, BigInteger count) {
		super();
		this.productlineid = productlineid;
		this.productline = productline;
		this.regional = regional;
		this.regionalid = regionalid;
		this.flm = flm;
		this.flmid = flmid;
		this.mp = mp;
		this.mpid = mpid;
		this.count = count;
	}
	private	Integer productlineid;
   private	String productline;
   private  String regional;
   private  Integer regionalid;
   private  String flm;
   private  Integer flmid;
   private  String mp;
   private  Integer mpid;
   private BigInteger count;
   
   
public Integer getProductlineid() {
	return productlineid;
}
public void setProductlineid(Integer productlineid) {
	this.productlineid = productlineid;
}
public String getProductline() {
	return productline;
}
public void setProductline(String productline) {
	this.productline = productline;
}
public String getRegional() {
	return regional;
}
public void setRegional(String regional) {
	this.regional = regional;
}
public Integer getRegionalid() {
	return regionalid;
}
public void setRegionalid(Integer regionalid) {
	this.regionalid = regionalid;
}
public String getFlm() {
	return flm;
}
public void setFlm(String flm) {
	this.flm = flm;
}
public Integer getFlmid() {
	return flmid;
}
public void setFlmid(Integer flmid) {
	this.flmid = flmid;
}
public String getMp() {
	return mp;
}
public void setMp(String mp) {
	this.mp = mp;
}
public Integer getMpid() {
	return mpid;
}
public void setMpid(Integer mpid) {
	this.mpid = mpid;
}
public BigInteger getCount() {
	return count;
}
public void setCount(BigInteger count) {
	this.count = count;
}

}
