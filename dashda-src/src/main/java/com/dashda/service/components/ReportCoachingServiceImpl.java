/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ReportCoachingDetailInputDTO;
import com.dashda.controllers.dto.ReportCoachingDetailOutputDTO;
import com.dashda.controllers.dto.ReportCoachingEmployeeSummaryInputDTO;
import com.dashda.controllers.dto.ReportCoachingEmployeeSummaryOutputDTO;
import com.dashda.controllers.dto.ReportCoachingSummaryInputDTO;
import com.dashda.data.entities.DoubleVisit;
import com.dashda.data.entities.ReportCoachingEmployeeSummary;
import com.dashda.data.entities.ReportCoachingSummary;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.ReportDao;
import com.dashda.exception.ReportCoachingServiceExcepion;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportCoachingServiceImpl extends ServicesManager implements ReportCoachingService {


	@Autowired
	ReportDao reportDao;
	
	public ReportCoachingServiceImpl() {
	}

	@Override
	public AppResponse summary(String username, ReportCoachingSummaryInputDTO reportCoachingSummaryInputDTO)
			throws ReportCoachingServiceExcepion, ParseException {
		
		List reportCoachingSummaryList = reportDao.generateSummary(reportCoachingSummaryInputDTO.getEmployeeIds()
				, reportCoachingSummaryInputDTO.getDateFrom(), reportCoachingSummaryInputDTO.getDateTo());
		
		List reportCoachingSummaryOutputDTOs = new ArrayList();
		for (Iterator iterator = reportCoachingSummaryList.iterator(); iterator.hasNext();) {
			ReportCoachingSummary reportCoachingSummary = (ReportCoachingSummary) iterator.next();
			ReportCoachingSummaryOutputDTO reportCoachingSummaryOutputDTO = new ReportCoachingSummaryOutputDTO();
			
			mapper.map(reportCoachingSummary, reportCoachingSummaryOutputDTO);
			reportCoachingSummaryOutputDTOs.add(reportCoachingSummaryOutputDTO);
		}
		
		return okListResponse(reportCoachingSummaryOutputDTOs);
	}

	@Override
	public AppResponse employeeSummary(String username,
			ReportCoachingEmployeeSummaryInputDTO reportCoachingEmployeeSummaryInputDTO)
			throws ReportCoachingServiceExcepion, ParseException {
		
		List reportCoachingEmployeeSummaryList = reportDao.generateEmployeeSummary(reportCoachingEmployeeSummaryInputDTO.getEmployeeId(), 
				reportCoachingEmployeeSummaryInputDTO.getDateFrom(), reportCoachingEmployeeSummaryInputDTO.getDateTo());
		
		List reportCoachingEmployeeSummaryOutputDTOs = new ArrayList();
		for (Iterator iterator = reportCoachingEmployeeSummaryList.iterator(); iterator.hasNext();) {
			ReportCoachingEmployeeSummary reportCoachingEmployeeSummary = (ReportCoachingEmployeeSummary) iterator.next();
			ReportCoachingEmployeeSummaryOutputDTO reportCoachingEmployeeSummaryOutputDTO = new ReportCoachingEmployeeSummaryOutputDTO();
			
			mapper.map(reportCoachingEmployeeSummary, reportCoachingEmployeeSummaryOutputDTO);
			reportCoachingEmployeeSummaryOutputDTOs.add(reportCoachingEmployeeSummaryOutputDTO);
		}
		return okListResponse(reportCoachingEmployeeSummaryOutputDTOs);
	}

	@Override
	public AppResponse coachingDetail(String username, ReportCoachingDetailInputDTO reportCoachingDetailInputDTO)
			throws ReportCoachingServiceExcepion, ParseException {
		
		
		List reportCoachingDetailOutputDTOs = new ArrayList<>();
//		ReportCoachingDetailOutputDTO reportCoachingDetailOutputDTO = new ReportCoachingDetailOutputDTO("TP1BU1" , "HOSSNY ABD RABO PVT CLINIC - EG_AC_105783"
//				, "Obstetrics and Gynecology", "ALEX CENTER 1", "2018-05-01");
		
		
		List<Visit> visits = reportDao.generateDetails(reportCoachingDetailInputDTO.getEmployeeId(), 
				DateUtilities.convertToDate(reportCoachingDetailInputDTO.getDateTime()
						, DateUtilities.DATE_FORMATE_PATTERN));
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			ReportCoachingDetailOutputDTO reportCoachingDetailOutputDTO = new ReportCoachingDetailOutputDTO(visit.getEmployeeByEmployeeId().getName()
					, visit.getServiceProvider().getEnName(), visit.getServiceProvider().getSpecialty().getName()
					, visit.getServiceProvider().getDistrict().getEnName(), DateUtilities.dateFormate(visit.getDatetime()));
			
			reportCoachingDetailOutputDTOs.add(reportCoachingDetailOutputDTO);
		}
		
		
		
		return okListResponse(reportCoachingDetailOutputDTOs);
	}

}
