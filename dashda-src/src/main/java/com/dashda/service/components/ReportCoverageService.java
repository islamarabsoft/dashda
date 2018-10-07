/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.Date;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.exception.ReportCoverageServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportCoverageService {

	AppResponse userCoverage(String username) throws ReportCoverageServiceException;
	AppResponse userCoverage(String username, String dateFrom, String dateTo) throws ReportCoverageServiceException, ParseException;

}
