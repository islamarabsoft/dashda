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
import com.dashda.controllers.dto.plan.CalendarActivityInputDTO;
import com.dashda.controllers.dto.visit.VisitReportInputDTO;
import com.dashda.enums.ReturnType;
import com.dashda.exception.PlanServiceException;
import com.dashda.exception.VisitReportException;
import com.dashda.exception.VisitServiceException;
import com.dashda.service.components.VisitReportService;


@RestController
@RequestMapping("/visitreport")
public class VisitReportController {

	@Autowired
	VisitReportService visitReportService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/visit-employess-filters")
	@RestResponseEntity(returnType = ReturnType.LIST)
	public Object getVisitEmployeesFilters() 
			throws ParseException,  VisitServiceException, VisitReportException{
		
			return visitReportService.getVisitEmployeesFilters();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-product-filter")
	@RestResponseEntity(returnType = ReturnType.LIST)
	public Object getVisitProductFilter() 
			throws ParseException,   VisitReportException{
		
			return visitReportService.getVisitProductFilter();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-product-line-filter")
	@RestResponseEntity(returnType = ReturnType.LIST)
	public Object getVisitProductLineFilter() 
			throws ParseException,   VisitReportException{
		
			return visitReportService.getVisitProductLineFilter();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get-specialty-filter")
	@RestResponseEntity(returnType = ReturnType.LIST)
	public Object getVisitSpecialtyFilter() 
			throws ParseException,   VisitReportException{
		
			return visitReportService.getVisitSpecialtyFilter();
	}
	
	

	@RequestMapping(method = RequestMethod.POST, value = "/get-visitreport-count")
	@RestResponseEntity(status = HttpStatus.OK, returnType = ReturnType.LIST)
	public Object getVisitsReportCount( 
			@Valid @RequestBody VisitReportInputDTO visitReportInputDTO)
					throws ParseException,  VisitReportException{
		
			return visitReportService.getVisitsReportCount(visitReportInputDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/get-visitreport-comments")
	@RestResponseEntity(status = HttpStatus.OK, returnType = ReturnType.LIST)
	public Object getVisitsReportComments( 
			@Valid @RequestBody VisitReportInputDTO visitReportInputDTO)
					throws ParseException,  VisitReportException{
		
			return visitReportService.getVisitsReportComments(visitReportInputDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/get-visitreport-count-by-day")
	@RestResponseEntity(status = HttpStatus.OK, returnType = ReturnType.LIST)
	public Object getVisitsReportCountByDay( 
			@Valid @RequestBody VisitReportInputDTO visitReportInputDTO)
					throws ParseException,  VisitReportException{
		
			return visitReportService.getVisitsReportCountByDay(visitReportInputDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/get-visitreport-details-by-day")
	@RestResponseEntity(status = HttpStatus.OK, returnType = ReturnType.LIST)
	public Object getVisitsReportDetailsByDay( 
			@Valid @RequestBody VisitReportInputDTO visitReportInputDTO)
					throws ParseException,  VisitReportException{
		
			return visitReportService.getVisitsReportDetailsByDay(visitReportInputDTO);
	}
	
	
}
