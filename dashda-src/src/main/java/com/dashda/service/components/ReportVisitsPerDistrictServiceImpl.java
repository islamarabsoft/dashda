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

import com.dashda.controllers.dto.report.ReportVisitsPerDistrictDetailsInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerDistrictDetailsOutputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerDistrictInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerDistrictOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportVisitsPerDistrict;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.report.ReportDistrictDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.ReportVisitsPerDistrictServiceExcepion;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportVisitsPerDistrictServiceImpl extends ServicesManager implements ReportVisitsPerDistrictService {

	@Autowired
	ReportDistrictDao reportDistrictDao;
	
	@Override
	public List visitsPerDistrict(String userName,
			@Valid ReportVisitsPerDistrictInputDTO reportVisitsPerDistrictInputDTO)
					throws ReportVisitsPerDistrictServiceExcepion, ParseException {

		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerDistrictServiceExcepion(e.getErrorCode());
		}
		
		List<ReportVisitsPerDistrict> reportVisitsPerDistricts = reportDistrictDao.getVisitsPerDistrict(employee,
				DateUtilities.convertToDate(reportVisitsPerDistrictInputDTO.getDateFrom(),
						DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(reportVisitsPerDistrictInputDTO.getDateTo(),
						DateUtilities.DATE_FORMATE_PATTERN));
		 
		 
		 List reportVisitsPerDistrictOutputDTOs = new ArrayList();

			for (Iterator iterator = reportVisitsPerDistricts.iterator(); iterator.hasNext();) {
				ReportVisitsPerDistrict reportVisitsPerDistrict = (ReportVisitsPerDistrict) iterator.next();

				ReportVisitsPerDistrictOutputDTO reportVisitsPerDistrictOutputDTO = new ReportVisitsPerDistrictOutputDTO();

				mapper.map(reportVisitsPerDistrict, reportVisitsPerDistrictOutputDTO);

				reportVisitsPerDistrictOutputDTOs.add(reportVisitsPerDistrictOutputDTO);
			}

			return reportVisitsPerDistrictOutputDTOs;
	}
	
	
	public List visitsPerDistrictDetails(String userName,
			@Valid ReportVisitsPerDistrictDetailsInputDTO reportVisitsPerDistrictDetailsInputDTO)
					throws ReportVisitsPerDistrictServiceExcepion, ParseException {

		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerDistrictServiceExcepion(e.getErrorCode());
		}
		
		List VisitsPerDistrictDetailsOutputDTOs = new ArrayList();
		
		List<Visit> visits = reportDistrictDao.getVisitsPerDistrictDetails(employee, reportVisitsPerDistrictDetailsInputDTO.getDistrictId(), 
				DateUtilities.convertToDate(reportVisitsPerDistrictDetailsInputDTO.getDateFrom(), DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(reportVisitsPerDistrictDetailsInputDTO.getDateTo(), DateUtilities.DATE_FORMATE_PATTERN));
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			
			ReportVisitsPerDistrictDetailsOutputDTO visitDetailOutputDTO = new ReportVisitsPerDistrictDetailsOutputDTO(visit.getId(), visit.getEmployeeByEmployeeId().getName()
				, visit.getEmployeeByEmployeeId().getManager().getName() 
				, DateUtilities.dateFormate(visit.getDatetime()), visit.getServiceProvider().getFirstName() + " " + visit.getServiceProvider().getLastName()
				, visit.getServiceProvider().getSpecialty().getName(), visit.getServiceProvider().getDistrict().getEnName());
			
			VisitsPerDistrictDetailsOutputDTOs.add(visitDetailOutputDTO);
		}
		
		return VisitsPerDistrictDetailsOutputDTOs;
	}

}
