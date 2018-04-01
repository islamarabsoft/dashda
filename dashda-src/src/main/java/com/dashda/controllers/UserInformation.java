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

import com.dashda.service.components.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_USER_INFO")
public class UserInformation extends AbstractController{

	@Autowired
	UserService userService;
	
	@RequestMapping("/user-info")
	public String userInformation(@AuthenticationPrincipal User user) throws JsonProcessingException {
		
		
		return jasonObjectmapper.writeValueAsString(userService.getUserInfo(user.getUsername()));
	}
}
