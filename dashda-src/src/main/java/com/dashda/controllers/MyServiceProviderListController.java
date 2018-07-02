/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.AssignServiceProviderDTO;
import com.dashda.controllers.dto.AssignServiceProviderInputDTO;
import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.controllers.dto.EmployeeDoctorDTO;
import com.dashda.controllers.dto.MyServiceProviderInputDTO;
import com.dashda.controllers.dto.UnAssignServiceProviderInputDTO;
import com.dashda.exception.ApiError;
import com.dashda.exception.MyServiceProvidersListServiceExceptionManager;
import com.dashda.service.components.MyServiceProvidersListService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_MY_DOCTORS_LIST")
public class MyServiceProviderListController extends AbstractController {

	@Autowired
	MyServiceProvidersListService myServiceProvidersListService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/my-service-provider")
	public ResponseEntity myServiceProvidersList(@AuthenticationPrincipal User user, 
			@RequestBody MyServiceProviderInputDTO myServiceProviderInputDTO) throws MyServiceProvidersListServiceExceptionManager, ParseException{
		
		return returnResponseEntityOk(myServiceProvidersListService.myServiceProvidersList(user.getUsername(), myServiceProviderInputDTO));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/assign-service-provider")
	public ResponseEntity<EmployeeDoctorDTO> assignDoctorToMyList(@AuthenticationPrincipal User user, 
			@Validated @RequestBody AssignServiceProviderInputDTO serviceProviderInputDTO) 
					throws MyServiceProvidersListServiceExceptionManager {
		
			return returnResponseEntityCreated(myServiceProvidersListService.
					assignServiceProviderToMyList(user.getUsername(), serviceProviderInputDTO.getServiceProviderId()));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unassign-service-provider")
	public ResponseEntity<EmployeeDoctorDTO> unassignDoctorToMyList(@AuthenticationPrincipal User user,
			@Validated @RequestBody UnAssignServiceProviderInputDTO unAssignServiceProviderInputDTO) 
					throws MyServiceProvidersListServiceExceptionManager {
		
			return returnResponseEntityAccepted(myServiceProvidersListService.
					unassignServiceProviderToMyList(user.getUsername(), unAssignServiceProviderInputDTO.getAssignedId()));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/serviceProviderName")
	public ResponseEntity serviceProviderNameList(@AuthenticationPrincipal User user) throws MyServiceProvidersListServiceExceptionManager, ParseException{
		
		return returnResponseEntityOk(myServiceProvidersListService.serviceProviderNameList(user.getUsername()));
	}
	
	
	
	
	
	
//	@RequestMapping(method = RequestMethod.POST, value = "/save-my-doctors-list")
//	public void saveMyDoctorsList(@AuthenticationPrincipal User user, @RequestBody List<Integer> doctors) throws MyServiceProvidersListServiceExceptionManager {
//			myServiceProvidersListService.saveMyServiceProvidersList(user.getUsername(), doctors);
//	}
	
}
