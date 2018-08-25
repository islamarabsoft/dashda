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

import com.dashda.controllers.dto.report.ReportVisitsPerSpecialtyDetailsInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerSpecialtyDetailsOutputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerSpecialtyInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerSpecialtyOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportVisitsPerSpecialty;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.report.ReportSpecialtyDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.ReportVisitsPerSpecialtyServiceExcepion;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportVisitsPerSpecialtyServiceImpl extends ServicesManager implements ReportVisitsPerSpecialtyService {

	@Autowired
	ReportSpecialtyDao reportSpecialtyDao;
	
	@Override
	public List visitsPerSpecialty(String userName,
			@Valid ReportVisitsPerSpecialtyInputDTO reportVisitsPerSpecialtyInputDTO)
					throws ReportVisitsPerSpecialtyServiceExcepion, ParseException {

		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerSpecialtyServiceExcepion(e.getErrorCode());
		}
		
		List<ReportVisitsPerSpecialty> reportVisitsPerSpecialtys = reportSpecialtyDao.getVisitsPerSpecialty(employee,
				DateUtilities.convertToDate(reportVisitsPerSpecialtyInputDTO.getDateFrom(),
						DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(reportVisitsPerSpecialtyInputDTO.getDateTo(),
						DateUtilities.DATE_FORMATE_PATTERN));
		 
		 
		 List reportVisitsPerSpecialtyOutputDTOs = new ArrayList();

			for (Iterator iterator = reportVisitsPerSpecialtys.iterator(); iterator.hasNext();) {
				ReportVisitsPerSpecialty reportVisitsPerSpecialty = (ReportVisitsPerSpecialty) iterator.next();

				ReportVisitsPerSpecialtyOutputDTO reportVisitsPerSpecialtyOutputDTO = new ReportVisitsPerSpecialtyOutputDTO();

				mapper.map(reportVisitsPerSpecialty, reportVisitsPerSpecialtyOutputDTO);

				reportVisitsPerSpecialtyOutputDTOs.add(reportVisitsPerSpecialtyOutputDTO);
			}

			return reportVisitsPerSpecialtyOutputDTOs;
	}
	
	
	public List visitsPerSpecialtyDetails(String userName,
			@Valid ReportVisitsPerSpecialtyDetailsInputDTO reportVisitsPerSpecialtyDetailsInputDTO)
					throws ReportVisitsPerSpecialtyServiceExcepion, ParseException {

		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerSpecialtyServiceExcepion(e.getErrorCode());
		}
		
		List VisitsPerSpecialtyDetailsOutputDTOs = new ArrayList();
		
		List<Visit> visits = reportSpecialtyDao.getVisitsPerSpecialtyDetails(employee, reportVisitsPerSpecialtyDetailsInputDTO.getSpecialtyId(), 
				DateUtilities.convertToDate(reportVisitsPerSpecialtyDetailsInputDTO.getDateFrom(), DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(reportVisitsPerSpecialtyDetailsInputDTO.getDateTo(), DateUtilities.DATE_FORMATE_PATTERN));
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			
			ReportVisitsPerSpecialtyDetailsOutputDTO visitDetailOutputDTO = new ReportVisitsPerSpecialtyDetailsOutputDTO(visit.getId(), visit.getEmployeeByEmployeeId().getName()
				, visit.getEmployeeByEmployeeId().getManager().getName() 
				, DateUtilities.dateFormate(visit.getDatetime()), visit.getServiceProvider().getFirstName() + " " + visit.getServiceProvider().getLastName()
				, visit.getServiceProvider().getSpecialty().getName(), visit.getServiceProvider().getSpecialty().getName());
			
			VisitsPerSpecialtyDetailsOutputDTOs.add(visitDetailOutputDTO);
		}
		
		return VisitsPerSpecialtyDetailsOutputDTOs;
	}

}
