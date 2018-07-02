/**
 * 
 */
package com.dashda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.ReportTargetVisitInputDTO;
import com.dashda.exception.ReportVisitServiceException;
import com.dashda.service.components.ReportVisitService;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportVisitController extends AbstractController {
	
	@Autowired
	ReportVisitService reportVisitService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/target-visits")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity getTargetVisits(@AuthenticationPrincipal User user
			, @RequestBody ReportTargetVisitInputDTO reportTargetVisitInputDTO) throws ReportVisitServiceException {
		
		return returnResponseEntityOk(reportVisitService.getTargetVisits(user.getUsername(), reportTargetVisitInputDTO));
	}

}
