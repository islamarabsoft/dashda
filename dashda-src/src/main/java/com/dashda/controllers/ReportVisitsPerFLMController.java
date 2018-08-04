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
import com.dashda.controllers.dto.ReportVisitsPerFLMDetailsInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerFLMInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerProductDetailsInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerProductInputDTO;
import com.dashda.exception.ReportVisitsPerFLMServiceExcepion;
import com.dashda.service.components.ReportVisitsPerFLMService;
import com.dashda.service.components.ReportVisitsPerProductService;
import com.dashda.service.components.ReportVisitsPerProductServiceExcepion;


/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportVisitsPerFLMController extends AbstractController {

	@Autowired
	ReportVisitsPerFLMService reportVisitsPerFLMService ;
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-flm")
	@Secured("ROLE_REPORT_VISITS_PER_FLM")
	public Object getVisitsPerFLM(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerFLMInputDTO reportVisitsPerFLMInputDTO) 
					throws ReportVisitsPerFLMServiceExcepion, ParseException {
		
		return reportVisitsPerFLMService.visitsPerFLM(user.getUsername(), reportVisitsPerFLMInputDTO);
	}
	
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-flm-details")
	@Secured("ROLE_REPORT_VISITS_PER_FLM")
	public Object getVisitsPerFLMDetails(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerFLMDetailsInputDTO reportVisitsPerFLMDetailsInputDTO)
					throws ReportVisitsPerFLMServiceExcepion, ParseException {
		 
		return reportVisitsPerFLMService.visitsPerFLMDetails(user.getUsername(), reportVisitsPerFLMDetailsInputDTO);
	}
}
