package com.dashda.service.components;

import java.text.ParseException;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.visit.VisitReportInputDTO;
import com.dashda.exception.VisitReportException;
import com.dashda.exception.VisitServiceException;

public interface VisitReportService {

	
	
	Object getVisitEmployeesFilters(String userName) throws VisitReportException, ParseException;

	Object getVisitProductFilter(String userName) throws VisitReportException, ParseException;
	
	Object getVisitProductLineFilter(String userName) throws VisitReportException, ParseException;
	Object getVisitSpecialtyFilter(String userName) throws VisitReportException, ParseException ;
	
	Object getVisitsReportCount(String userName, VisitReportInputDTO visitReportInputDTO) throws VisitReportException, ParseException ;
	
	Object getVisitsReportCountByDay(String userName, VisitReportInputDTO visitReportInputDTO) throws VisitReportException, ParseException ;
	
	Object getVisitsReportDetailsByDay(String userName, VisitReportInputDTO visitReportInputDTO) throws VisitReportException, ParseException ;

	
	Object getVisitsReportComments(String userName, VisitReportInputDTO visitReportInputDTO) throws VisitReportException, ParseException ;

}
