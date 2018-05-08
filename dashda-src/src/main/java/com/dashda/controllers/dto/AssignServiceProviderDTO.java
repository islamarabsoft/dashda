/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class AssignServiceProviderDTO implements AbstractDTO{

	private Integer id;
	private String serviceProviderName;
	private String speciality;
	private String governorateName;
	private String assignedId;
	private String districtName;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getGovernorateName() {
		return governorateName;
	}
	public void setGovernorateName(String governorateName) {
		this.governorateName = governorateName;
	}
	public String getAssignedId() {
		return assignedId;
	}
	public void setAssignedId(String assignedId) {
		this.assignedId = assignedId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	
}
