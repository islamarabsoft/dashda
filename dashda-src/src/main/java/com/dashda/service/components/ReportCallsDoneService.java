/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.exception.ReportCallsDoneServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportCallsDoneService {

	AppResponse userCallsDone(String username) throws ReportCallsDoneServiceException;
	AppResponse userCallsDone(String username, String dateFrom, String dateTo) throws ReportCallsDoneServiceException, ParseException;
}
