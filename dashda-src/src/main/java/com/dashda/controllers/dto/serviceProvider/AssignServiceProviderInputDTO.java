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
public class AssignServiceProviderInputDTO implements AbstractDTO {

	@Digits(fraction=0, integer=8)
	private int serviceProviderId;

	public int getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(int serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	
	
}
