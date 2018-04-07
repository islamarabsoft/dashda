/**
 * 
 */
package com.dashda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		return jsonObjectmapper.writeValueAsString(myDoctorsListService.myDoctorsList(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save-my-doctors-list")
	public void saveMyDoctorsList(@AuthenticationPrincipal User user, @RequestBody List<Integer> doctors) throws JsonProcessingException {
			myDoctorsListService.saveMyDoctorsList(user.getUsername(), doctors);
	}
}
