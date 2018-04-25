/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.exception.MyDoctorsListServiceExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface MyDoctorsListService {

	public AppResponse myDoctorsList(String username) throws MyDoctorsListServiceExceptionManager, ParseException;

	public void saveMyDoctorsList(String username, List<Integer> doctors)throws MyDoctorsListServiceExceptionManager;

	public AppResponse assignDoctorToMyList(String username, Integer doctorId) throws MyDoctorsListServiceExceptionManager;

	public AppResponse unassignDoctorToMyList(String username, int assignedId) throws MyDoctorsListServiceExceptionManager;

}
