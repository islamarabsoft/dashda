package com.dashda.controllers.dto;

public class VisitDTO {

	private int visitId;
	
	private int doctorId;
	
	private String visitDate;
	
	private int employeeId;
	
	private String employeeName;

	/**
	 * @return the visitId
	 */
	public int getVisitId() {
		return visitId;
	}

	/**
	 * @param visitId the visitId to set
	 */
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}


	/**
	 * @return the visitDate
	 */
	public String getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	
}
