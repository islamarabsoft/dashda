/**
 * 
 */
package com.dashda.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.EmployeeUserDTO;
import com.dashda.controllers.dto.UserDTO;
import com.dashda.service.components.UserService;
import com.dashda.service.components.UserServiceExceptioManager;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_USER_INFO")
@RequestMapping("/user")
public class UserInformation extends AbstractController{

	@Autowired
	UserService userService;
	
	@RequestMapping("/user-info")
	public String userInformation(@AuthenticationPrincipal User user) throws JsonProcessingException {
		
		
		return jsonObjectmapper.writeValueAsString(userService.getUserInfo(user.getUsername()));
	}
	
	@Secured("ROLE_SYSTEM_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/create-user")
	public void createUser(@AuthenticationPrincipal User user, @Valid @RequestBody UserDTO userDTO) throws UserServiceExceptioManager {
		userService.createUser(userDTO);
	}
	
	
	@Secured("ROLE_SYSTEM_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/create-employee-user")
	public void createEmployeeUser(@AuthenticationPrincipal User user, @Valid @RequestBody EmployeeUserDTO employeeUserDTO) throws UserServiceExceptioManager {
		userService.createEmployeeUser(employeeUserDTO);
	}
}
