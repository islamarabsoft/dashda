/**
 * 
 */
package com.dashda.service.components;

import java.util.List;

import com.dashda.controllers.dto.VisitDTO;
import com.dashda.exception.VisitServiceException;

/**
 * @author mhanafy
 *
 */
public interface VisitService {

	public List<VisitDTO> visitItemsList(String username) throws VisitServiceException;

	public void completeVisits(String username, List<Integer> visits)throws VisitServiceException;

	public void dicardVisits(String username, List<Integer> visits)throws VisitServiceException;

}
