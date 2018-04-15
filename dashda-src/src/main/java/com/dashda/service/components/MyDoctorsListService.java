/**
 * 
 */
package com.dashda.service.components;

import java.util.List;

import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.exception.MyDoctorsListServiceExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface MyDoctorsListService {

	public List<DoctorDTO> myDoctorsList(String username) throws MyDoctorsListServiceExceptionManager;

	public void saveMyDoctorsList(String username, List<Integer> doctors)throws MyDoctorsListServiceExceptionManager;

}
