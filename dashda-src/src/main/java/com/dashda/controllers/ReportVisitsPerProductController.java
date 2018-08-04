/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.annotation.RestResponseEntity;
import com.dashda.controllers.dto.ReportVisitsPerProductDetailsInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerProductInputDTO;
import com.dashda.service.components.ReportVisitsPerProductService;
import com.dashda.service.components.ReportVisitsPerProductServiceExcepion;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class ReportVisitsPerProductController extends AbstractController {

	@Autowired
	ReportVisitsPerProductService reportVisitsPerProductService ;
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-product")
	@Secured("ROLE_REPORT_VISITS_PER_PRODUCT")
	public Object getVisitsPerProduct(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerProductInputDTO reportVisitsPerProductInputDTO) throws ReportVisitsPerProductServiceExcepion, ParseException {
		 
		return reportVisitsPerProductService.visitsPerProduct(user.getUsername(), reportVisitsPerProductInputDTO);
	}
	
	
	@RestResponseEntity(status = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, value = "/visits-per-product-details")
	@Secured("ROLE_REPORT_VISITS_PER_PRODUCT")
	public Object getVisitsPerProductDetails(@AuthenticationPrincipal User user
			, @Valid @RequestBody ReportVisitsPerProductDetailsInputDTO reportVisitsPerProductDetailsInputDTO) throws ReportVisitsPerProductServiceExcepion, ParseException {
		 
		return reportVisitsPerProductService.visitsPerProductDetails(user.getUsername(), reportVisitsPerProductDetailsInputDTO);
	}
}
