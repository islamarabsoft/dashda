/**
 * 
 */
package com.dashda.service.components;

import javax.validation.Valid;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.UserDTO;
import com.dashda.controllers.dto.employee.EmployeeUserDTO;
import com.dashda.exception.UserServiceExceptioManager;

/**
 * @author mhanafy
 *
 */
public interface UserService {

	
	public AppResponse getUserInfo(String username) throws UserServiceExceptioManager;

	public void createUser(@Valid UserDTO userDTO) throws UserServiceExceptioManager;
	
	public AppResponse createEmployeeUser(@Valid EmployeeUserDTO employeeUserDTO) throws UserServiceExceptioManager;

	public UserDTO authorizationInfo(String username, String password) throws UserServiceExceptioManager;
	
}
