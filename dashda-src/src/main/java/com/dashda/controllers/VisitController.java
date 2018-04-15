/**
 * 
 */
package com.dashda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.exception.VisitServiceException;
import com.dashda.service.components.VisitService;
import com.fasterxml.jackson.core.JsonProcessingException;

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
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/visits")
	public String visitItemsList(@AuthenticationPrincipal User user) throws JsonProcessingException, VisitServiceException{
		return jsonObjectmapper.writeValueAsString(visitService.visitItemsList(user.getUsername()));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/complete-visits")
	public void completeVisits(@AuthenticationPrincipal User user, @RequestBody List<Integer> visits) throws VisitServiceException {
		visitService.completeVisits(user.getUsername(), visits);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/discard-visits")
	public void discardVisits(@AuthenticationPrincipal User user, @RequestBody List<Integer> visits) throws VisitServiceException {
		visitService.dicardVisits(user.getUsername(), visits);
	}
}
