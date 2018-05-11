/**
 * 
 */
package com.dashda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.exception.ApprovalServiceException;
import com.dashda.service.components.ApprovalService;

/**
 * @author mhanafy
 *
 */
@RestController
@Secured("ROLE_SCHEDULE_ADMIN")
public class ApprovalController extends AbstractController {

	@Autowired
	ApprovalService approvalService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/pending-approval")
	public ResponseEntity listOfPendingApprovalRequests(@AuthenticationPrincipal User user) 
			throws ApprovalServiceException {
		return returnResponseEntityOk(
				approvalService.getListOfPendingApprovalrequests(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/approve-request")
	public ResponseEntity approveRequest(@AuthenticationPrincipal User user) 
			throws ApprovalServiceException {
		return returnResponseEntityOk(
				approvalService.getListOfPendingApprovalrequests(user.getUsername()));
	}
}
