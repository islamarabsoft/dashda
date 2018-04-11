/**
 * 
 */
package com.dashda.service.components;

import javax.validation.Valid;

import com.dashda.controllers.dto.UserDTO;

/**
 * @author mhanafy
 *
 */
public interface UserService {

	
	public UserDTO getUserInfo(String username);

	public void createUser(@Valid UserDTO userDTO) throws UserServiceExceptioManager;
}
