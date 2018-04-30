/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class MyDoctorDTO implements AbstractDTO{
	
	private Integer id;
	private String assignedId;
	private String doctorName;
	private String speciality;
	private String scheduleId;
	private String scheduleDate;
	private String lastVisitStatus;
	private String lastVisitDate;
	private String governorateName;
	private String districtName;
	
	
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
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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

	
	
}
