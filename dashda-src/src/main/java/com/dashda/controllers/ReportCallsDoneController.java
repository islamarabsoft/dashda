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
import com.dashda.exception.ReportCallsDoneServiceException;
import com.dashda.service.components.ReportCallsDoneService;


/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportCallsDoneController extends AbstractController {

	@Autowired
	ReportCallsDoneService reportCallsDoneService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/report-calls-done")
	@Secured("ROLE_REPORT_CALLS_DONE_HOME")
	public ResponseEntity homeCallsDone(@AuthenticationPrincipal User user) throws ReportCallsDoneServiceException {
		return returnResponseEntityOk(reportCallsDoneService.userCallsDone(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/report-calls-done-by-period")
	@Secured("ROLE_REPORT_CALLS_DONE_HOME")
	public ResponseEntity homeCallsDone(@AuthenticationPrincipal User user, 
			@Valid @RequestBody DateFromToInputDTO dateFromToInputDTO) throws ReportCallsDoneServiceException, ParseException {
		return returnResponseEntityOk(reportCallsDoneService.userCallsDone(user.getUsername(), 
				dateFromToInputDTO.getDateFrom(), dateFromToInputDTO.getDateTo()));
	}
}
