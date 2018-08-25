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

import com.dashda.controllers.dto.report.ReportTargetVisitInputDTO;
import com.dashda.controllers.dto.report.ReportUnVisitInputDTO;
import com.dashda.controllers.dto.visit.VisitDetailInputDTO;
import com.dashda.controllers.dto.visit.VisitsListInputDTO;
import com.dashda.controllers.dto.visit.VisitsPerEmployeeInputDTO;
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
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/unvisit")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity unvisit(@AuthenticationPrincipal User user
			, @RequestBody ReportUnVisitInputDTO reportUntVisitInputDTO) throws ReportVisitServiceException {
		
		return returnResponseEntityOk(reportVisitService.getUnVisits(user.getUsername(), reportUntVisitInputDTO));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/visits-list")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity visitsList(@AuthenticationPrincipal User user
			, @RequestBody VisitsListInputDTO visitsListInputDTO) throws ReportVisitServiceException, ParseException {
		
		return returnResponseEntityOk(reportVisitService.getVisitsList(user.getUsername(), visitsListInputDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/visit-detail")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity visitDetail(@AuthenticationPrincipal User user
			,@Valid @RequestBody VisitDetailInputDTO visitDetailInputDTO) throws ReportVisitServiceException, ParseException {
		
		return returnResponseEntityOk(reportVisitService.getVisitDetail(user.getUsername(), visitDetailInputDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-employee")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity visitsPerEmployee(@AuthenticationPrincipal User user
			,@Valid @RequestBody VisitsPerEmployeeInputDTO visitsPerEmployeeInputDTO) throws ReportVisitServiceException, ParseException {
		
		return returnResponseEntityOk(reportVisitService.visitsPerEmployee(user.getUsername(), visitsPerEmployeeInputDTO));
	}
	
}
