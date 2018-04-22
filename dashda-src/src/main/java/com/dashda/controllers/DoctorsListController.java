/**
 * 
 */
package com.dashda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.exception.DoctorServiceExceptionManager;
import com.dashda.service.components.DoctorsListService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_DOCTORS_LIST")
public class DoctorsListController extends AbstractController{

	@Autowired
	DoctorsListService doctorsListService;
	
	
	@RequestMapping("/doctors")
	public ResponseEntity<ListResponse> doctorsList(@AuthenticationPrincipal User user) throws JsonProcessingException, DoctorServiceExceptionManager {
		
		return new ResponseEntity(doctorsListService.doctorsList(user.getUsername()), HttpStatus.OK);
	}
	
}
