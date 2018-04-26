/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.RequestInput;
import com.dashda.controllers.dto.VisitDTO;
import com.dashda.controllers.dto.VisitInquiryDTO;
import com.dashda.exception.VisitServiceException;

/**
 * @author mhanafy
 *
 */
public interface VisitService {

	public AppResponse visitItemsList(String username, VisitInquiryDTO visitInquiryDTO) throws VisitServiceException, ParseException;

	public AppResponse completeVisits(String username, RequestInput requestInput)throws VisitServiceException;

	public AppResponse dicardVisits(String username, RequestInput requestInput)throws VisitServiceException;

}
