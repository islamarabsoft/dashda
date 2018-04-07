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
public interface MyDoctorsListService {

	public List<DoctorDTO> myDoctorsList(String username);

	public void saveMyDoctorsList(String username, List<Integer> doctors);

}
