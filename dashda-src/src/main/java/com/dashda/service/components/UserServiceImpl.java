/**
 * 
 */
package com.dashda.service.components;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.EmployeeUserDTO;
import com.dashda.controllers.dto.UserDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ClaimDTO;
import com.dashda.data.entities.Account;
import com.dashda.data.entities.Contact;
import com.dashda.data.entities.District;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Governorate;
import com.dashda.data.entities.Permission;
import com.dashda.data.entities.User;
import com.dashda.data.entities.UserRolePermission;
import com.dashda.data.repositories.ContactDao;
import com.dashda.data.repositories.EmployeeDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.enums.PermissionTypeEnum;
import com.dashda.exception.UserServiceExceptioManager;

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
	EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder generatePasswordEncoder;
	
	private UserDTO userDTO;
	private User user;
	private Contact contact;
	private Account account;
	private District district;

	private Governorate governorate;
	
	@Override
	public AppResponse getUserInfo(String username) throws UserServiceExceptioManager {
		
		user = userDao.findUserByUsername(username);
				
		userDTO = prepareUserDTOObject(user);
		
		return okResponse(userDTO, "User Info");
	}

	@Override
	public UserDTO authorizationInfo(String username, String password) throws UserServiceExceptioManager {
		user = userDao.findActiveUserByUsernameAndPassword(username, generatePasswordEncoder.encode(password));
		
		userDTO = prepareUserDTOObject(user);
		
		return userDTO;
	}

	@Override
	public void createUser(UserDTO userDTO) throws UserServiceExceptioManager {
		createUserEntity(userDTO);
	
	}


	@Override
	public AppResponse createEmployeeUser(@Valid EmployeeUserDTO employeeUserDTO) throws UserServiceExceptioManager {
		UserDTO userDTO = createUserEntity(employeeUserDTO);
		return createResponse(userDTO, "Employee Created Successfully");
	}

	private UserDTO createUserEntity(UserDTO userDTO) throws UserServiceExceptioManager {
		
		if(userDao.findUserByUsername(userDTO.getUsername()) != null)
			throw new UserServiceExceptioManager(ERROR_CODE_1008);
		
		contact = new Contact();
		user = new User();
		account = new Account(Integer.parseInt(userDTO.getAccountId()));
		district = new District(Integer.parseInt(userDTO.getDistrictId()));
		governorate = new Governorate(Integer.parseInt(userDTO.getGovernorateId()));
		
		Employee employee = null;
		
		//initiate contact object
		contact.setDistrict(district);
		contact.setGovernorate(governorate);
		
		mapper.map(userDTO, contact);
		
		//used by create EmployeeUser Object
		if( userDTO instanceof EmployeeUserDTO) {
			
			EmployeeUserDTO employeeUserDTO = (EmployeeUserDTO) userDTO;
			employee = new Employee();
			
			Employee manager = null;
			
			//if this employee have manager relation
			if(employeeUserDTO.getManagerId() != null) {
				
				int managerId = Integer.parseInt(employeeUserDTO.getManagerId());
				manager = employeeDao.findEmployeeByID(managerId);
				
				if(manager == null) {
					throw new UserServiceExceptioManager(ERROR_CODE_1009);
				}
			}
			
			mapper.map(userDTO, employee);
			
			employee.setManager(manager);
		}
		
		
		mapper.map(userDTO, user);
		
		user.setPassword(generatePasswordEncoder.encode(userDTO.getPassword()));
		
		contactDao.createContact(contact);
		
		
		// persist on DB
		if(employee != null) {
			employee.setContact(contact);
			employee.setAccount(account);
			employeeDao.createEmployee(employee);
		}
			
		
		
		if(employee != null)
			user.setEmployee(employee);
		user.setContact(contact);
		
		userDao.createUser(user);
		
		userDTO.setId(user.getId());
		if(employee != null)
			((EmployeeUserDTO)userDTO).setEmployeeId(employee.getId());
		return userDTO;
	}

private UserDTO prepareUserDTOObject(User user) throws UserServiceExceptioManager {
		
	if(user == null)
		throw new UserServiceExceptioManager(ERROR_CODE_1014);
		
		List<ClaimDTO> claims = new ArrayList<ClaimDTO>();
		ClaimDTO claim = null;
		Permission permission = null;
		
		if(user.getEmployee() != null) {
			userDTO = new EmployeeUserDTO();
			mapper.map(user.getEmployee(), userDTO);
			if(user.getEmployee().getManager() != null)
				((EmployeeUserDTO)userDTO).setManagerId(user.getEmployee().getManager().getId()+"");
			
		}else {
			userDTO = new UserDTO();
		}
			
		
		mapper.map(user.getContact(), userDTO);
		mapper.map(user, userDTO);
		
		
		for (Iterator<UserRolePermission> userRolePermissionIt = 
				user.getUserRole().getUserRolePermissions().iterator(); userRolePermissionIt.hasNext();) {
			
			permission = userRolePermissionIt.next().getPermission();
			
			if(permission.getPermissionType().getId() == PermissionTypeEnum.CLIENT_TEMPLATE.getValue()) {
				claim = new ClaimDTO(permission.getId(), permission.getName());
				claims.add(claim);
			}
			
		}
		
		userDTO.setClaims(claims);
		userDTO.setPassword("***********");
		
		return userDTO;
		
	}
	
	
}
