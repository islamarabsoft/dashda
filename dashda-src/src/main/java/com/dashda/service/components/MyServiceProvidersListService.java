/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.serviceProvider.DoctorDTO;
import com.dashda.controllers.dto.serviceProvider.MyServiceProviderInputDTO;
import com.dashda.exception.MyServiceProvidersListServiceExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface MyServiceProvidersListService {

	public AppResponse myServiceProvidersList(String username, MyServiceProviderInputDTO myServiceProviderInputDTO) throws MyServiceProvidersListServiceExceptionManager, ParseException;

	public void saveMyServiceProvidersList(String username, List<Integer> serviceProviders, int serviceProviderTypeId)throws MyServiceProvidersListServiceExceptionManager;

	public AppResponse assignServiceProviderToMyList(String username, Integer serviceProviderId) throws MyServiceProvidersListServiceExceptionManager;

	public AppResponse unassignServiceProviderToMyList(String username, int assignedId) throws MyServiceProvidersListServiceExceptionManager;

	public AppResponse serviceProviderNameList(String username) throws MyServiceProvidersListServiceExceptionManager;

}
