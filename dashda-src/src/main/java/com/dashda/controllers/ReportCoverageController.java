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

import com.dashda.controllers.dto.DateFromToInputDTO;
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/report-coverage-home-by-period")
	@Secured("ROLE_REPORT_COVERAGE_HOME")
	public ResponseEntity homeCoverage(@AuthenticationPrincipal User user
			, @Valid @RequestBody DateFromToInputDTO dateFromToInputDTO) throws ReportCoverageServiceException, ParseException {
		return returnResponseEntityOk(reportCoverageService.userCoverage(user.getUsername()
				, dateFromToInputDTO.getDateFrom(), dateFromToInputDTO.getDateTo()));
	}
}
