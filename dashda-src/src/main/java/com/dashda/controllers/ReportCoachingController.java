/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.report.ReportCoachingDetailInputDTO;
import com.dashda.controllers.dto.report.ReportCoachingEmployeeSummaryInputDTO;
import com.dashda.controllers.dto.report.ReportCoachingSummaryInputDTO;
import com.dashda.exception.ReportCoachingServiceExcepion;
import com.dashda.service.components.ReportCoachingService;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
@Secured("ROLE_REPORT_COACHING")
public class ReportCoachingController extends AbstractController {

	@Autowired
	ReportCoachingService reportCoachingService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/coaching-summary")
	public ResponseEntity coachingSummary(@AuthenticationPrincipal User user
			, @RequestBody ReportCoachingSummaryInputDTO reportCoachingSummaryInputDTO) throws ReportCoachingServiceExcepion, ParseException {
		
		return returnResponseEntityOk(reportCoachingService.summary(user.getUsername(), reportCoachingSummaryInputDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/coaching-employee-summary")
	public ResponseEntity coachingEmployeeSummary(@AuthenticationPrincipal User user
			, @RequestBody ReportCoachingEmployeeSummaryInputDTO reportCoachingEmployeeSummaryInputDTO) throws ReportCoachingServiceExcepion, ParseException {
		
		return returnResponseEntityOk(reportCoachingService.employeeSummary(user.getUsername(), reportCoachingEmployeeSummaryInputDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/coaching-detail")
	public ResponseEntity coachingdetail(@AuthenticationPrincipal User user
			, @RequestBody ReportCoachingDetailInputDTO reportCoachingDetailInputDTO) throws ReportCoachingServiceExcepion, ParseException {

		return returnResponseEntityOk(reportCoachingService.coachingDetail(user.getUsername(), reportCoachingDetailInputDTO));
	}
}

