/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;

/**
 * @author mhanafy
 *
 */
public interface EmployeeService {

	public AppResponse getManagerList(String username) throws EmployeeServiceException;

}
