/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.VisitAddCommentInputDTO;
import com.dashda.controllers.dto.VisitCompleteInputDTO;
import com.dashda.controllers.dto.VisitListInputDTO;
import com.dashda.exception.VisitServiceException;
import com.dashda.service.components.VisitService;

/**
 * @author mhanafy
 *
 */

@RestController
@RequestMapping("/visit")
@Secured("ROLE_VISIT_CONTRIBUTOR")
public class VisitController extends AbstractController {

	@Autowired
	VisitService visitService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/visits")
	public ResponseEntity visitItemsList(@AuthenticationPrincipal User user, 
			@Valid @RequestBody VisitListInputDTO visitListInputDTO) throws VisitServiceException, ParseException{
		
		return returnResponseEntityOk(visitService.visitItemsList(user.getUsername(), visitListInputDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/complete-visit")
	public ResponseEntity completeVisits(@AuthenticationPrincipal User user, 
			@Valid @RequestBody VisitCompleteInputDTO visitCompleteInput) throws VisitServiceException {
		
		return returnResponseEntityOk(visitService.completeVisit(user.getUsername(), visitCompleteInput));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/visit-comment")
	public ResponseEntity visitComment(@AuthenticationPrincipal User user, 
			@Valid @RequestBody VisitAddCommentInputDTO visitAddCommentInputDTO) throws VisitServiceException {
		
		return returnResponseEntityOk(visitService.addComment(user.getUsername(), visitAddCommentInputDTO));
	}
	
	
	
	
	
	
	
	
	
/**	
	@RequestMapping(method = RequestMethod.POST, value = "/discard-visits")
	public ResponseEntity discardVisits(@AuthenticationPrincipal User user, 
			@RequestBody RequestInput requestInput) throws VisitServiceException {
		
		return returnResponseEntityOk(visitService.dicardVisits(user.getUsername(), requestInput));
	}
	**/
}
