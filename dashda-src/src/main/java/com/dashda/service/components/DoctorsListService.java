/**
 * 
 */
package com.dashda.service.components;

import java.util.List;

import com.dashda.controllers.dto.DoctorDTO;

/**
 * @author mhanafy
 *
 */
public interface DoctorsListService {

	public List<DoctorDTO> doctorsList(String username);
}
