/**
 * 
 */
package com.dashda.controllers.dto.serviceProvider;

import javax.validation.constraints.Digits;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class ServiceProviderInputDTO implements AbstractDTO {

	@Digits(fraction = 0 , integer = 1)
	private int serviceTypeId;

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	
	
}
