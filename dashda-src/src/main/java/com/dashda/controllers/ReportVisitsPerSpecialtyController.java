/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.annotation.RestResponseEntity;
import com.dashda.controllers.dto.ReportVisitsPerSpecialtyDetailsInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerSpecialtyInputDTO;
import com.dashda.exception.ReportVisitsPerSpecialtyServiceExcepion;
import com.dashda.service.components.ReportVisitsPerSpecialtyService;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportVisitsPerSpecialtyController extends AbstractController {

	@Autowired
	ReportVisitsPerSpecialtyService reportVisitsPerSpecialtyService ;
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-specialty")
	@Secured("ROLE_REPORT_VISITS_PER_SPECIALTY")
	public Object getVisitsPerSpecialty(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerSpecialtyInputDTO reportVisitsPerSpecialtyInputDTO) 
					throws ReportVisitsPerSpecialtyServiceExcepion, ParseException {
		
		return reportVisitsPerSpecialtyService.visitsPerSpecialty(user.getUsername(), reportVisitsPerSpecialtyInputDTO);
	}
	
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-specialty-details")
	@Secured("ROLE_REPORT_VISITS_PER_SPECIALTY")
	public Object getVisitsPerSpecialtyDetails(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerSpecialtyDetailsInputDTO reportVisitsPerSpecialtyDetailsInputDTO)
					throws ReportVisitsPerSpecialtyServiceExcepion, ParseException {
		 
		return reportVisitsPerSpecialtyService.visitsPerSpecialtyDetails(user.getUsername(), reportVisitsPerSpecialtyDetailsInputDTO);
	}
}
