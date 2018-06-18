/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;

import javax.validation.Valid;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.VisitAddCommentInputDTO;
import com.dashda.controllers.dto.VisitAdhocVisitInputDTO;
import com.dashda.controllers.dto.VisitCompleteInputDTO;
import com.dashda.controllers.dto.VisitListInputDTO;
import com.dashda.exception.VisitServiceException;

/**
 * @author mhanafy
 *
 */
public interface VisitService {

	public AppResponse visitItemsList(String username, VisitListInputDTO visitInquiryDTO) throws VisitServiceException, ParseException;

	public AppResponse completeVisit(String username, VisitCompleteInputDTO visitCompleteInput)throws VisitServiceException;

	public AppResponse addComment(String username, VisitAddCommentInputDTO visitAddCommentInputDTO)throws VisitServiceException;
	
	public void discardAllVisitsItemsBeforeHoursDuration(int hours);

	public AppResponse adhocVisit(String username, VisitAdhocVisitInputDTO visitAdhocVisitInput) throws VisitServiceException, ParseException;
	
	
	
	//public AppResponse dicardVisits(String username, RequestInput requestInput)throws VisitServiceException;

}
