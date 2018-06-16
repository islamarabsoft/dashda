/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ManagerOutputDto;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.EmployeeHierarchyDao;
import com.dashda.data.repositories.UserDao;

/**
 * @author mhanafy
 *
 */
@Service
public class EmployeeServiceImpl extends ServicesManager implements EmployeeService {

	@Autowired
	EmployeeHierarchyDao employeeHierarchyDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public AppResponse getManagerList(String username) throws EmployeeServiceException {
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		
		if(employee == null)
			throw new EmployeeServiceException(ERROR_CODE_1001);
		
		List<EmployeeHierarchy> employeeHierarchies = employeeHierarchyDao.getManagers(employee);
		List managers = new ArrayList();
		
		ManagerOutputDto ManagerOutputDto = null;
		for (Iterator iterator = employeeHierarchies.iterator(); iterator.hasNext();) {
			EmployeeHierarchy employeeHierarchy = (EmployeeHierarchy) iterator.next();
			
			ManagerOutputDto = new ManagerOutputDto();
			
			ManagerOutputDto.setId(employeeHierarchy.getManager().getId());
			ManagerOutputDto.setName(employeeHierarchy.getManager().getContact().getFirstName()
					+" "+ employeeHierarchy.getManager().getContact().getLastName());
			
			managers.add(ManagerOutputDto);
		}
		
		return okListResponse(managers, "List Size is : " + managers.size());
	}

}
