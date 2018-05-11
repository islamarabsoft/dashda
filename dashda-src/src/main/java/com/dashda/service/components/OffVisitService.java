/**
 * 
 */
package com.dashda.service.components;


import java.text.ParseException;

import javax.validation.Valid;

import com.dashda.controllers.dto.AddOffVisitInputDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.DeleteOffVisitInputDTO;
import com.dashda.controllers.dto.UpdateOffVisitInputDTO;
import com.dashda.exception.OffVisitServiceException;

/**
 * @author mhanafy
 *
 */
public interface OffVisitService {

	public AppResponse createOffVisit(String username, AddOffVisitInputDTO addVisitInputDTO) 
			throws OffVisitServiceException, ParseException;

	public AppResponse updateOffVisit(String username, UpdateOffVisitInputDTO updateOffVisitInputDTO)
			throws OffVisitServiceException, ParseException;

	public AppResponse deleteOffVisit(String username, DeleteOffVisitInputDTO deleteOffVisitInputDTO)
			throws OffVisitServiceException;

	public AppResponse getOffVisitreasons();

	public AppResponse getListofOffVisit(String username)throws OffVisitServiceException, ParseException;

}
