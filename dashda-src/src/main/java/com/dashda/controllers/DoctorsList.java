/**
 * 
 */
package com.dashda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.service.components.DoctorsListService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_DOCTORS_LIST")
public class DoctorsList extends AbstractController{

	@Autowired
	DoctorsListService doctorsListService;
	
	
	@RequestMapping("/doctors")
	public String doctorsList(@AuthenticationPrincipal User user) throws JsonProcessingException {
		
		return jsonObjectmapper.writeValueAsString(doctorsListService.doctorsList(user.getUsername()));
	}
	
}
