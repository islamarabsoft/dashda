package com.dashda.service.components;

import java.text.ParseException;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.visit.VisitReportInputDTO;
import com.dashda.exception.VisitReportException;
import com.dashda.exception.VisitServiceException;

public interface VisitReportService {

	
	
	Object getVisitEmployeesFilters() throws VisitReportException, ParseException;

	Object getVisitProductFilter() throws VisitReportException, ParseException;
	
	Object getVisitProductLineFilter() throws VisitReportException, ParseException;
	Object getVisitSpecialtyFilter() throws VisitReportException, ParseException ;
	
	Object getVisitsReportCount(VisitReportInputDTO visitReportInputDTO) throws VisitReportException, ParseException ;
}
