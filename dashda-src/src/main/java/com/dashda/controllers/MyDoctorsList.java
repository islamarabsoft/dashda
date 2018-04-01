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

import com.dashda.service.components.MyDoctorsListService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_MY_DOCTORS_LIST")
public class MyDoctorsList extends AbstractController {

	@Autowired
	MyDoctorsListService myDoctorsListService;
	
	@RequestMapping("/my-doctors")
	public String myDoctorsList(@AuthenticationPrincipal User user) throws JsonProcessingException{
		
		return jasonObjectmapper.writeValueAsString(myDoctorsListService.myDoctorsList(user.getUsername()));
	}
}
