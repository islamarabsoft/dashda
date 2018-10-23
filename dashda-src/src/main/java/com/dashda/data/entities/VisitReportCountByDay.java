package com.dashda.data.entities;

import java.math.BigInteger;
import java.sql.Timestamp;

public class VisitReportCountByDay {

	private Timestamp day;
	private BigInteger count;
	
	
	public VisitReportCountByDay(Timestamp day, BigInteger count) {
		super();
		this.day = day;
		this.count = count;
	}
	public VisitReportCountByDay() {
		// TODO Auto-generated constructor stub
	}
	public Timestamp getDay() {
		return day;
	}
	public void setDay(Timestamp timestamp) {
		this.day = timestamp;
	}
	public BigInteger getCount() {
		return count;
	}
	public void setCount(BigInteger count) {
		this.count = count;
	}
	
}
