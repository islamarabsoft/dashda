package com.dashda.controllers.dto.visit;

import java.math.BigInteger;

public class VisitReportCountByDayOutputDTO {
	
	 private BigInteger count;
	 private String date;
	 
	 
	public VisitReportCountByDayOutputDTO(BigInteger count, String date) {
		super();
		this.count = count;
		this.date = date;
	}
	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
