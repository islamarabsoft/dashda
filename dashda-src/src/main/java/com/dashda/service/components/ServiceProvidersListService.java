/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.serviceProvider.ServiceProviderInputDTO;
import com.dashda.exception.ServiceProviderServiceExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface ServiceProvidersListService {

	public AppResponse serviceProvidersList(String username, ServiceProviderInputDTO serviceProviderInputDTO) throws ServiceProviderServiceExceptionManager;
}
