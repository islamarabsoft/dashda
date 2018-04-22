/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.exception.DoctorServiceExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface DoctorsListService {

	public AppResponse doctorsList(String username) throws DoctorServiceExceptionManager;
}
