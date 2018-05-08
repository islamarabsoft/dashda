/**
 * 
 */
package com.dashda.service.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.EmployeeDistrictDTO;
import com.dashda.data.entities.District;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeesCoveredDistrict;
import com.dashda.data.repositories.DistrictDao;
import com.dashda.data.repositories.EmployeeDao;
import com.dashda.data.repositories.EmployeeCoveredDistrictDao;
import com.dashda.exception.EmployeDistrictServiceException;

/**
 * @author mhanafy
 *
 */
@Service
public class EmployeeDistrictServiceImpl extends ServicesManager implements EmployeeDistrictService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	DistrictDao districtDao;
	
	@Autowired
	EmployeeCoveredDistrictDao employeesCoveredDistrictDao;
	
	@Override
	public AppResponse assignEmployeeToDistrict(String employeeId, String districtId)
			throws EmployeDistrictServiceException {

		EmployeeDistrictDTO employeeDistrictDTO = new EmployeeDistrictDTO();
		employeeDistrictDTO.setDistrictId(districtId);
		employeeDistrictDTO.setEmployeeId(employeeId);
		
		Employee employee = employeeDao.findEmployeeByID(Integer.parseInt(employeeId));
		if(employee == null)
			throw new EmployeDistrictServiceException(ERROR_CODE_1015);
		
		District district = districtDao.findDistrictById(Integer.parseInt(districtId));
		if(district == null)
			throw new EmployeDistrictServiceException(ERROR_CODE_1016);
		EmployeesCoveredDistrict coveredDistrict = new EmployeesCoveredDistrict();
		coveredDistrict.setEmployee(employee);
		coveredDistrict.setDistrict(district);
		
		employeesCoveredDistrictDao.assignDistrictToEmployee(coveredDistrict);
		
		employeeDistrictDTO.setAssignId(coveredDistrict.getId()+"");
		return createResponse(employeeDistrictDTO, "District Assigned to Employee Successullty");
	}

}
