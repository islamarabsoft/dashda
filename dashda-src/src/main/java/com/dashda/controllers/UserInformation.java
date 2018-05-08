/**
 * 
 */
package com.dashda.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.EmployeeUserDTO;
import com.dashda.controllers.dto.UserDTO;
import com.dashda.exception.PermissionServiceExceptioManager;
import com.dashda.exception.UserServiceExceptioManager;
import com.dashda.service.components.PermissionService;
import com.dashda.service.components.UserService;
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
	
	@Autowired
	PermissionService permissionService;
	
	@Secured("ROLE_USER_INFO")
	@RequestMapping("/user-info")
	public ResponseEntity userInformation(@AuthenticationPrincipal User user) throws JsonProcessingException, UserServiceExceptioManager {
		
		return returnResponseEntityOk(userService.getUserInfo(user.getUsername()));
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "/authorization-info", params = {"username!=", "password!="})
//	public String authorizationInfo(@RequestParam(required = true) String username, 
//			@RequestParam(required = true) String password) throws JsonProcessingException, UserServiceExceptioManager {
//		return jsonObjectmapper.writeValueAsString(userService.authorizationInfo(username, password));
//	}
	
	@Secured({"ROLE_USER_INFO", "ROLE_SYSTEM_ADMIN"})
	@RequestMapping(method = RequestMethod.POST, value = "/create-user")
	public void createUser(@AuthenticationPrincipal User user, @Valid @RequestBody UserDTO userDTO) throws UserServiceExceptioManager {
		userService.createUser(userDTO);
	}
	
	
	@Secured({"ROLE_USER_INFO", "ROLE_SYSTEM_ADMIN"})
	@RequestMapping(method = RequestMethod.POST, value = "/create-employee-user")
	public ResponseEntity createEmployeeUser(@AuthenticationPrincipal User user, @Valid @RequestBody EmployeeUserDTO employeeUserDTO) throws UserServiceExceptioManager {
		return returnResponseEntityCreated(userService.createEmployeeUser(employeeUserDTO));
	}
	
	@Secured({"ROLE_USER_INFO", "ROLE_SYSTEM_ADMIN", "ROLE_ADMIN_CREATE_TEMPLATE"})
	@RequestMapping(method = RequestMethod.POST, value = "/create-template-and-assign-to-user-role", params = {"permission!=", "assignedToUserRoleId!="})
	public void createTemplateAndAssignToUserGroup(@RequestParam(required = true) String permission,
			@RequestParam(required = true) int assignedToUserRoleId) throws PermissionServiceExceptioManager {
		
		permissionService.createTemplateAndAssignToUserGroup(permission, assignedToUserRoleId);
	}
}
