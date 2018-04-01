/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.UserDTO;

/**
 * @author mhanafy
 *
 */
public interface UserService {

	public String findListOfUsers();
	
	public UserDTO getUserInfo(String username);
}
