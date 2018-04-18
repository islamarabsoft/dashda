/**
 * 
 */
package com.dashda.service.components;

import javax.validation.Valid;

import com.dashda.controllers.dto.EmployeeUserDTO;
import com.dashda.controllers.dto.UserDTO;

/**
 * @author mhanafy
 *
 */
public interface UserService {

	
	public UserDTO getUserInfo(String username) throws UserServiceExceptioManager;

	public void createUser(@Valid UserDTO userDTO) throws UserServiceExceptioManager;
	
	public void createEmployeeUser(@Valid EmployeeUserDTO employeeUserDTO) throws UserServiceExceptioManager;

	public UserDTO authorizationInfo(String username, String password) throws UserServiceExceptioManager;
}
