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

import com.dashda.exception.ReportCoverageServiceException;
import com.dashda.service.components.ReportCoverageService;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportCoverageController extends AbstractController {

	@Autowired
	ReportCoverageService reportCoverageService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/report-coverage-home")
	@Secured("ROLE_REPORT_COVERAGE_HOME")
	public ResponseEntity homeCoverage(@AuthenticationPrincipal User user) throws ReportCoverageServiceException {
		return returnResponseEntityOk(reportCoverageService.userCoverage(user.getUsername()));
	}
	
	
}
