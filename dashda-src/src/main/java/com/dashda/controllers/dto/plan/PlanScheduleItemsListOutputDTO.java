/**
 * 
 */
package com.dashda.controllers.dto.plan;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class PlanScheduleItemsListOutputDTO implements AbstractDTO {

	private int id;
	private int serviceProviderId;
	private String serviceProviderName;
	private String specialty;
	private String district;
	private String date;
	private String status;
	
	
	public PlanScheduleItemsListOutputDTO() {
		super();
	}


	public PlanScheduleItemsListOutputDTO(int id, int serviceProviderId, String serviceProviderName, String specialty,
			String district, String date, String status) {
		super();
		this.id = id;
		this.serviceProviderId = serviceProviderId;
		this.serviceProviderName = serviceProviderName;
		this.specialty = specialty;
		this.district = district;
		this.date = date;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getServiceProviderId() {
		return serviceProviderId;
	}


	public void setServiceProviderId(int serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}


	public String getServiceProviderName() {
		return serviceProviderName;
	}


	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
