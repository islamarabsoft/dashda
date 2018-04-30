/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.exception.EmployeDistrictServiceException;

/**
 * @author mhanafy
 *
 */
public interface EmployeeDistrictService {

	public AppResponse assignEmployeeToDistrict(String employeeId, String districtId) throws EmployeDistrictServiceException;
}
