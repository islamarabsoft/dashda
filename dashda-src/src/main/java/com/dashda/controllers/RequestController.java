/**
 * 
 */
package com.dashda.controllers;

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

import com.dashda.controllers.dto.InputRequestApprovalDTO;
import com.dashda.exception.ApprovalServiceException;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.service.components.RequestService;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_SCHEDULE_ADMIN")
public class RequestController extends AbstractController {

	@Autowired
	RequestService requestService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/pending-approval")
	public ResponseEntity listOfPendingApprovalRequests(@AuthenticationPrincipal User user) 
			throws ApprovalServiceException {
		return returnResponseEntityOk(
				requestService.getListOfPendingApprovalrequests(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/approve-request")
	public ResponseEntity approveRequest(@AuthenticationPrincipal User user
			,@Valid @RequestBody InputRequestApprovalDTO inputRequestApprovalDTO) 
			throws ApprovalServiceException {
		return returnResponseEntityOk(
				requestService.approveRequest(user.getUsername(), inputRequestApprovalDTO));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reject-request")
	public ResponseEntity rejectRequest(@AuthenticationPrincipal User user
			, @Valid @RequestBody InputRequestRejectDTO inputRequestRejectDTO) 
					throws ApprovalServiceException {
		return returnResponseEntityOk(
				requestService.rejectRequest(user.getUsername(), inputRequestRejectDTO));
	}
}
