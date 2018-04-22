/**
 * 
 */
package com.dashda.controllers;

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

import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.exception.ApiError;
import com.dashda.exception.MyDoctorsListServiceExceptionManager;
import com.dashda.service.components.MyDoctorsListService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_MY_DOCTORS_LIST")
public class MyDoctorsListController extends AbstractController {

	@Autowired
	MyDoctorsListService myDoctorsListService;
	
	@RequestMapping("/my-doctors")
	public ResponseEntity<List<DoctorDTO>> myDoctorsList(@AuthenticationPrincipal User user) throws MyDoctorsListServiceExceptionManager{
		
		return new ResponseEntity (myDoctorsListService.myDoctorsList(user.getUsername()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save-my-doctors-list")
	public void saveMyDoctorsList(@AuthenticationPrincipal User user, @RequestBody List<Integer> doctors) throws MyDoctorsListServiceExceptionManager {
			myDoctorsListService.saveMyDoctorsList(user.getUsername(), doctors);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/assign-doctor")
	public ResponseEntity<EmployeeDoctorDTO> assignDoctorToMyList(@AuthenticationPrincipal User user,@Validated @RequestBody EmployeeDoctorDTO employeeDoctorDTO) throws MyDoctorsListServiceExceptionManager {
			return returnResponseEntityCreated(myDoctorsListService.assignDoctorToMyList(user.getUsername(), employeeDoctorDTO.getDoctorId()));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unassign-doctor")
	public ResponseEntity<EmployeeDoctorDTO> unassignDoctorToMyList(@AuthenticationPrincipal User user,@Validated @RequestBody EmployeeDoctorDTO employeeDoctorDTO) throws MyDoctorsListServiceExceptionManager {
			return returnResponseEntityAccepted(myDoctorsListService.unassignDoctorToMyList(user.getUsername(), employeeDoctorDTO.getAssignedId()));
	}
	
}
