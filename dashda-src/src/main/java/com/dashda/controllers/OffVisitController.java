/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;

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

import com.dashda.controllers.dto.AddOffVisitInputDTO;
import com.dashda.controllers.dto.DeleteOffVisitInputDTO;
import com.dashda.controllers.dto.UpdateOffVisitInputDTO;
import com.dashda.exception.ApprovalServiceException;
import com.dashda.exception.OffVisitServiceException;
import com.dashda.service.components.OffVisitService;


/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_OFF_Visit_CREATOR")
public class OffVisitController extends AbstractController {

	@Autowired
	OffVisitService offVisitService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/create-offvisit")
	public ResponseEntity createOffVisit(@AuthenticationPrincipal User user, 
			@Valid @RequestBody AddOffVisitInputDTO addOffVisitInputDTO) throws OffVisitServiceException, ParseException {
		
		return returnResponseEntityCreated(offVisitService
								.createOffVisit(user.getUsername(), addOffVisitInputDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/update-offvisit")
	public ResponseEntity updateOffVisit(@AuthenticationPrincipal User user, 
			@Valid @RequestBody UpdateOffVisitInputDTO updateOffVisitInputDTO) 
					throws OffVisitServiceException, ParseException {
		
		return returnResponseEntityOk(offVisitService
								.updateOffVisit(user.getUsername(), updateOffVisitInputDTO));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/delete-offvisit")
	public ResponseEntity deleteOffVisit(@AuthenticationPrincipal User user, 
			@Valid @RequestBody DeleteOffVisitInputDTO deleteOffVisitInputDTO) throws OffVisitServiceException {
		
		return returnResponseEntityAccepted(offVisitService
								.deleteOffVisit(user.getUsername(), deleteOffVisitInputDTO));
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/off-visit-reason")
	public ResponseEntity listOfPendingApprovalRequests(@AuthenticationPrincipal User user) 
			throws ApprovalServiceException {
		return returnResponseEntityOk(
				offVisitService.getOffVisitreasons());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/off-visits")
	public ResponseEntity listOfOffVisits(@AuthenticationPrincipal User user) 
			throws ApprovalServiceException, OffVisitServiceException, ParseException {
		return returnResponseEntityOk(
				offVisitService.getListofOffVisit(user.getUsername()));
	}
	
}
