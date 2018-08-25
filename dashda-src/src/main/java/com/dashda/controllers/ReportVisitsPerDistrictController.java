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
import com.dashda.controllers.dto.report.ReportVisitsPerDistrictDetailsInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerDistrictInputDTO;
import com.dashda.exception.ReportVisitsPerDistrictServiceExcepion;
import com.dashda.service.components.ReportVisitsPerDistrictService;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportVisitsPerDistrictController extends AbstractController {

	@Autowired
	ReportVisitsPerDistrictService reportVisitsPerDistrictService ;
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-district")
	@Secured("ROLE_REPORT_VISITS_PER_DISTRICT")
	public Object getVisitsPerDistrict(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerDistrictInputDTO reportVisitsPerDistrictInputDTO) 
					throws ReportVisitsPerDistrictServiceExcepion, ParseException {
		
		return reportVisitsPerDistrictService.visitsPerDistrict(user.getUsername(), reportVisitsPerDistrictInputDTO);
	}
	
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-district-details")
	@Secured("ROLE_REPORT_VISITS_PER_DISTRICT")
	public Object getVisitsPerDistrictDetails(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerDistrictDetailsInputDTO reportVisitsPerDistrictDetailsInputDTO)
					throws ReportVisitsPerDistrictServiceExcepion, ParseException {
		 
		return reportVisitsPerDistrictService.visitsPerDistrictDetails(user.getUsername(), reportVisitsPerDistrictDetailsInputDTO);
	}
}
