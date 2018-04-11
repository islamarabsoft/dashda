/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.UserDTO;
import com.dashda.controllers.dto.UserPermessionDTO;
import com.dashda.data.entities.Account;
import com.dashda.data.entities.Contact;
import com.dashda.data.entities.District;
import com.dashda.data.entities.Governorate;
import com.dashda.data.entities.User;
import com.dashda.data.entities.UserRolePermission;
import com.dashda.data.repositories.ContactDao;
import com.dashda.data.repositories.UserDao;

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

	@Autowired
	private ContactDao contactDao;
	
	
	@Autowired
	private PasswordEncoder generatePasswordEncoder;
	
	
	private User user;
	private Contact contact;

	private District district;

	private Governorate governorate;
	
	@Override
	public UserDTO getUserInfo(String username) {
		
		UserPermessionDTO userPermessionDTO;
		UserRolePermission userRolePermission;
		
		UserDTO userDTO = new UserDTO();
		List<UserPermessionDTO> userPermessionDTOs = new ArrayList<UserPermessionDTO>();
		
		//Map primitive attributes 
		user = userDao.findUserByUsername(username);
		mapper.map(user.getContact(), userDTO);
		mapper.map(user.getEmployee(), userDTO);
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


	@Override
	public void createUser(UserDTO userDTO) throws UserServiceExceptioManager {
		System.out.println(generatePasswordEncoder.encode(userDTO.getPassword()));
		if(userDao.findUserByUsername(userDTO.getUsername()) != null)
			throw new UserServiceExceptioManager(ERROR_CODE_1008);
		
		contact = new Contact();
		user = new User();
		Account account = new Account(Integer.parseInt(userDTO.getAccountId()));
		district = new District(Integer.parseInt(userDTO.getDistrictId()));
		
		governorate = new Governorate(Integer.parseInt(userDTO.getGovernorateId()));
		
		
		contact.setDistrict(district);
		contact.setGovernorate(governorate);
		
		mapper.map(userDTO, contact);
		
		
		user.setAccount(account);
		
		mapper.map(userDTO, user);
		System.out.println("am new new");
		user.setPassword(generatePasswordEncoder.encode(userDTO.getPassword()));
						
		// persist on DB
		contactDao.createContact(contact);
		user.setContact(contact);
		//user.setCreatedAt("");
		userDao.createUser(user);
			
	}

	
	
}
