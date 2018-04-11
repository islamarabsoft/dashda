/**
 * 
 */
package com.dashda.service.components;

import java.util.List;

import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.exception.DoctorServiceExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface DoctorsListService {

	public List<DoctorDTO> doctorsList(String username) throws DoctorServiceExceptionManager;
}
