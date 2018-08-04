/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.ReportVisitsPerFLMDetailsInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerFLMDetailsOutputDTO;
import com.dashda.controllers.dto.ReportVisitsPerFLMInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerFLMOutputDTO;
import com.dashda.controllers.dto.ReportVisitsPerProductOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportVisitsPerFLM;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.ReportFLMDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.ReportVisitsPerFLMServiceExcepion;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportVisitsPerFLMServiceImpl extends ServicesManager implements ReportVisitsPerFLMService {

	@Autowired
	ReportFLMDao reportFLMDao;
	
	@Override
	public List visitsPerFLM(String userName, @Valid ReportVisitsPerFLMInputDTO reportVisitsPerFLMInputDTO)
			throws ReportVisitsPerFLMServiceExcepion, ParseException {
		
		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerFLMServiceExcepion(e.getErrorCode());
		}
		
		List<ReportVisitsPerFLM> reportVisitsPerFLMs = reportFLMDao.getVisitsPerFLM(employee,
				DateUtilities.convertToDate(reportVisitsPerFLMInputDTO.getDateFrom(),
						DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(reportVisitsPerFLMInputDTO.getDateTo(),
						DateUtilities.DATE_FORMATE_PATTERN));
		 
		 
		 List reportVisitsPerFLMOutputDTOs = new ArrayList();

			for (Iterator iterator = reportVisitsPerFLMs.iterator(); iterator.hasNext();) {
				ReportVisitsPerFLM reportVisitsPerFLM = (ReportVisitsPerFLM) iterator.next();

				ReportVisitsPerFLMOutputDTO reportVisitsPerFLMOutputDTO = new ReportVisitsPerFLMOutputDTO();

				mapper.map(reportVisitsPerFLM, reportVisitsPerFLMOutputDTO);

				reportVisitsPerFLMOutputDTOs.add(reportVisitsPerFLMOutputDTO);
			}

			return reportVisitsPerFLMOutputDTOs;
			
			
	}

	@Override
	public List visitsPerFLMDetails(String userName,
			@Valid ReportVisitsPerFLMDetailsInputDTO reportVisitsPerFLMDetailsInputDTO)
			throws ReportVisitsPerFLMServiceExcepion, ParseException {
		
		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerFLMServiceExcepion(e.getErrorCode());
		}
		
		List VisitsPerFLMDetailsOutputDTOs = new ArrayList();
		
		List<Visit> visits = reportFLMDao.getVisitsPerFLMDetails(employee, reportVisitsPerFLMDetailsInputDTO.getFlmId(), 
				DateUtilities.convertToDate(reportVisitsPerFLMDetailsInputDTO.getDateFrom(), DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(reportVisitsPerFLMDetailsInputDTO.getDateTo(), DateUtilities.DATE_FORMATE_PATTERN));
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			
			ReportVisitsPerFLMDetailsOutputDTO visitDetailOutputDTO = new ReportVisitsPerFLMDetailsOutputDTO(visit.getId(), visit.getEmployeeByEmployeeId().getName()
				, visit.getEmployeeByEmployeeId().getManager().getName() 
				, DateUtilities.dateFormate(visit.getDatetime()), visit.getServiceProvider().getFirstName() + " " + visit.getServiceProvider().getLastName()
				, visit.getServiceProvider().getSpecialty().getName(), visit.getServiceProvider().getDistrict().getEnName());
			
			VisitsPerFLMDetailsOutputDTOs.add(visitDetailOutputDTO);
		}
		
		return VisitsPerFLMDetailsOutputDTOs;
	}

}
