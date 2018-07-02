/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ReportCoachingDetailInputDTO;
import com.dashda.controllers.dto.ReportCoachingEmployeeSummaryInputDTO;
import com.dashda.controllers.dto.ReportCoachingSummaryInputDTO;
import com.dashda.exception.ReportCoachingServiceExcepion;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportCoachingService {

	AppResponse summary(String username, ReportCoachingSummaryInputDTO reportCoachingSummaryInputDTO) throws ReportCoachingServiceExcepion, ParseException;

	AppResponse employeeSummary(String username,
			ReportCoachingEmployeeSummaryInputDTO reportCoachingEmployeeSummaryInputDTO)throws ReportCoachingServiceExcepion, ParseException;

	AppResponse coachingDetail(String username, ReportCoachingDetailInputDTO reportCoachingDetailInputDTO)throws ReportCoachingServiceExcepion, ParseException;

}
