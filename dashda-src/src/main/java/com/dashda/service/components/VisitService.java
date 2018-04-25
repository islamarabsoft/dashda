/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.VisitDTO;
import com.dashda.controllers.dto.VisitInquiryDTO;
import com.dashda.exception.VisitServiceException;

/**
 * @author mhanafy
 *
 */
public interface VisitService {

	public AppResponse visitItemsList(String username, VisitInquiryDTO visitInquiryDTO) throws VisitServiceException, ParseException;

	public void completeVisits(String username, List<Integer> visits)throws VisitServiceException;

	public void dicardVisits(String username, List<Integer> visits)throws VisitServiceException;

}
