/**
 * 
 */
package com.dashda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public String visitItemsList(@AuthenticationPrincipal User user) throws JsonProcessingException{
		return jsonObjectmapper.writeValueAsString(visitService.visitItemsList(user.getUsername()));
	}
}
