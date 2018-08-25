/**
 * 
 */
package com.dashda.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.serviceProvider.ServiceProviderInputDTO;
import com.dashda.exception.ServiceProviderServiceExceptionManager;
import com.dashda.service.components.ServiceProvidersListService;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_DOCTORS_LIST")
public class ServiceProviderListController extends AbstractController{

	@Autowired
	ServiceProvidersListService serviceProvidersListService;
	
	
	@RequestMapping(method = RequestMethod.POST, value ="/service-providers")
	public ResponseEntity<ListResponse> serviceProvidersList(@AuthenticationPrincipal User user, 
			@Valid @RequestBody ServiceProviderInputDTO serviceProviderInputDTO) throws ServiceProviderServiceExceptionManager {
		
		return new ResponseEntity(serviceProvidersListService.serviceProvidersList(user.getUsername(), serviceProviderInputDTO), HttpStatus.OK);
	}
	
}
