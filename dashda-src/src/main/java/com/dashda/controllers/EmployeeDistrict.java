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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.EmployeeDistrictDTO;
import com.dashda.exception.EmployeDistrictServiceException;
import com.dashda.exception.UserServiceExceptioManager;
import com.dashda.service.components.EmployeeDistrictService;

/**
 * @author mhanafy
 *
 */
@RestController
public class EmployeeDistrict extends AbstractController {

	
	@Autowired
	EmployeeDistrictService employeeDistrictService;
	
	
	@Secured({"ROLE_USER_INFO", "ROLE_SYSTEM_ADMIN"})
	@RequestMapping(method = RequestMethod.POST, value = "/employee-district")
	public ResponseEntity createUser(@AuthenticationPrincipal User user, 
			@Valid @RequestBody EmployeeDistrictDTO employeeDistrictDTO) 
					throws UserServiceExceptioManager, EmployeDistrictServiceException {
		
		return returnResponseEntityCreated(employeeDistrictService.
				assignEmployeeToDistrict(employeeDistrictDTO.getEmployeeId(),
				employeeDistrictDTO.getDistrictId()));
	}
}
