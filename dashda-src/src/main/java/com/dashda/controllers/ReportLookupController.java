/**
 * 
 */
package com.dashda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.service.components.ReportLookupService;
import com.dashda.service.components.ReportLookupServiceException;

import org.springframework.security.core.userdetails.User;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportLookupController extends AbstractController {

	@Autowired
	ReportLookupService reportLookupService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/lookup-subordinates")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity subordinates(@AuthenticationPrincipal User user) throws ReportLookupServiceException {
		
		return returnResponseEntityOk(reportLookupService.findSubordinates(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/lookup-subordinates-without-low-level")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity subordinatesWithoutLowLevel(@AuthenticationPrincipal User user) throws ReportLookupServiceException {
		
		return returnResponseEntityOk(reportLookupService.findSubordinatesWithoutLowLevel(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/lookup-subordinates-low-level")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity subordinatesLowLevel(@AuthenticationPrincipal User user) throws ReportLookupServiceException {
		
		return returnResponseEntityOk(reportLookupService.findSubordinatesLowLevel(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/lookup-assigned-service-provider-district")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity serviceProviderDistrict(@AuthenticationPrincipal User user) throws ReportLookupServiceException {
		
		return returnResponseEntityOk(reportLookupService.findServiceProviderDistrict(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/lookup-assigned-service-provider-specialty")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity serviceProviderSpecialty(@AuthenticationPrincipal User user) throws ReportLookupServiceException {
		
		return returnResponseEntityOk(reportLookupService.findServiceProviderSpecialty(user.getUsername()));
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/lookup-assigned-service-provider")
	@Secured("ROLE_REPORT_LOOKUP")
	public ResponseEntity serviceProvider(@AuthenticationPrincipal User user) throws ReportLookupServiceException {
		
		return returnResponseEntityOk(reportLookupService.findServiceProvider(user.getUsername()));
	}	
	
}
