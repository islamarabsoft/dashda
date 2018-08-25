package com.dashda.controllers.dto.visit;

import com.dashda.controllers.dto.AbstractDTO;

public class VisitDTO implements AbstractDTO{

	private int visitId;
	private int serviceProviderId;
	private String serviceProviderName;
	private String serviceProviderTypeId;
	private int specialtyId;
	private String visitDate;
	private int employeeId;
	private String employeeName;
	private String status;
	private String statusId;
	private String comment;


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
	public int getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setServiceProviderId(int doctorId) {
		this.serviceProviderId = doctorId;
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

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	
	public String getServiceProviderTypeId() {
		return serviceProviderTypeId;
	}

	public void setServiceProviderTypeId(String serviceProviderTypeId) {
		this.serviceProviderTypeId = serviceProviderTypeId;
	}

	public int getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	
	
}
