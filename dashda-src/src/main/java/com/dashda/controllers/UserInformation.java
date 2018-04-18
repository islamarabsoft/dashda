/**
 * 
 */
package com.dashda.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/user")
public class UserInformation extends AbstractController{

	@Autowired
	UserService userService;
	
	@Secured("ROLE_USER_INFO")
	@RequestMapping("/user-info")
	public String userInformation(@AuthenticationPrincipal User user) throws JsonProcessingException, UserServiceExceptioManager {
		
		
		return jsonObjectmapper.writeValueAsString(userService.getUserInfo(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/authorization-info", params = {"username!=", "password!="})
	public String authorizationInfo(@RequestParam(required = true) String username, 
			@RequestParam(required = true) String password) throws JsonProcessingException, UserServiceExceptioManager {
		return jsonObjectmapper.writeValueAsString(userService.authorizationInfo(username, password));
	}
	
	@Secured({"ROLE_USER_INFO", "ROLE_SYSTEM_ADMIN"})
	@RequestMapping(method = RequestMethod.POST, value = "/create-user")
	public void createUser(@AuthenticationPrincipal User user, @Valid @RequestBody UserDTO userDTO) throws UserServiceExceptioManager {
		userService.createUser(userDTO);
	}
	
	
	@Secured({"ROLE_USER_INFO", "ROLE_SYSTEM_ADMIN"})
	@RequestMapping(method = RequestMethod.POST, value = "/create-employee-user")
	public void createEmployeeUser(@AuthenticationPrincipal User user, @Valid @RequestBody EmployeeUserDTO employeeUserDTO) throws UserServiceExceptioManager {
		userService.createEmployeeUser(employeeUserDTO);
	}
}
