/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class ServiceProviderLookupDTO implements AbstractDTO {

	private int id;
	private String serviceProviderName;
	private int specialtyId;
	private int districtId;
	
	
	public ServiceProviderLookupDTO() {
		super();
	}
	
	public ServiceProviderLookupDTO(int id, String serviceProviderName) {
		super();
		this.id = id;
		this.serviceProviderName = serviceProviderName;
	}	
	public ServiceProviderLookupDTO(int id, String serviceProviderName, int specialtyId, int districtId) {
		super();
		this.id = id;
		this.serviceProviderName = serviceProviderName;
		this.specialtyId = specialtyId;
		this.districtId = districtId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}


	public int getSpecialtyId() {
		return specialtyId;
	}


	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	
	
}
