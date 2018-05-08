/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class MyServiceProviderDTO implements AbstractDTO{
	
	private Integer id;
	private String assignedId;
	private String serviceProviderName;
	private String speciality;
	private String scheduleId;
	private String scheduleDate;
	private String lastVisitStatus;
	private String lastVisitDate;
	private String governorateName;
	private String districtName;
	private String serviceProviderTypeId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAssignedId() {
		return assignedId;
	}
	public void setAssignedId(String assignedId) {
		this.assignedId = assignedId;
	}

	public String getServiceProviderName() {
		return serviceProviderName;
	}
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getLastVisitStatus() {
		return lastVisitStatus;
	}
	public void setLastVisitStatus(String lastVisitStatus) {
		this.lastVisitStatus = lastVisitStatus;
	}
	public String getLastVisitDate() {
		return lastVisitDate;
	}
	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	public String getGovernorateName() {
		return governorateName;
	}
	public void setGovernorateName(String governorateName) {
		this.governorateName = governorateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getServiceProviderTypeId() {
		return serviceProviderTypeId;
	}
	public void setServiceProviderTypeId(String serviceProviderTypeId) {
		this.serviceProviderTypeId = serviceProviderTypeId;
	}

	
	
}
