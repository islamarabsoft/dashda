/**
 * 
 */
package com.dashda.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.service.components.EmployeeService;
import com.dashda.service.components.EmployeeServiceException;

/**
 * @author mhanafy
 *
 */
@RestController
public class EmployeeController extends AbstractController {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/manager-of-employee")
	@Secured("ROLE_VISIT_CONTRIBUTOR")
	public ResponseEntity managerOfEmployee(@AuthenticationPrincipal User user) 
						throws EmployeeServiceException {
		
		return returnResponseEntityOk(employeeService.getManagerList(user.getUsername()));
	}
}
