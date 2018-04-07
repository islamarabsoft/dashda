/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dashda.controllers.dto.UserDTO;
import com.dashda.controllers.dto.UserPermessionDTO;
import com.dashda.data.entities.Permission;
import com.dashda.data.entities.User;
import com.dashda.data.entities.UserRolePermission;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.UserDaoImpl;
import com.google.apphosting.client.serviceapp.AuthService.UserPermissions;

/**
 * @author mhanafy
 *
 */
@Service("userService")
public class UserServiceImpl extends ServicesManager implements UserService {

	/* (non-Javadoc)
	 * @see com.dashda.service.components.UserService#findListOfUsers()
	 */

	@Autowired
	private UserDao userDao;


	@Override
	public String findListOfUsers() {
    	
		//beginTransaction();
		
		List<User> users = userDao.findAll();
		
		String name = ((User)users.get(0)).getName();
		//commitTransaction();
		return name;
		
	}


	@Override
	public UserDTO getUserInfo(String username) {
		
		UserPermessionDTO userPermessionDTO;
		UserRolePermission userRolePermission;
		
		UserDTO userDTO = new UserDTO();
		List<UserPermessionDTO> userPermessionDTOs = new ArrayList<UserPermessionDTO>();
		
		//Map primitive attributes 
		User user = userDao.findUserByUsername(username);
		user.getEmployee().getEmployee();
		
		mapper.map(user, userDTO);
		
		for(Iterator<UserRolePermission> userRolePermissionIt = user.getUserRole().getUserRolePermissions().iterator(); userRolePermissionIt.hasNext();) {
			userRolePermission = userRolePermissionIt.next();
			userRolePermission.getPermission().getPermission();
			
			userPermessionDTO = new UserPermessionDTO();
			userPermessionDTO.setId(userRolePermission.getId());
			userPermessionDTO.setPermession(userRolePermission.getPermission().getPermission());
			
			userPermessionDTOs.add(userPermessionDTO);
		}
		
		userDTO.setUserPermessionDTOs(userPermessionDTOs);
		
		
		return userDTO;
	}

}
