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

import com.dashda.controllers.dto.ReportVisitsPerProductDetailsInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerProductInputDTO;
import com.dashda.controllers.dto.ReportVisitsPerProductOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.ReportProductDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportVisitsPerProductServiceImpl extends ServicesManager implements ReportVisitsPerProductService {

	@Autowired
	ReportProductDao reportProductDao;

	@Override
	public List visitsPerProduct(String userName, ReportVisitsPerProductInputDTO reportVisitsPerProductInputDTO)
			throws ReportVisitsPerProductServiceExcepion, ParseException {

		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerProductServiceExcepion(e.getErrorCode());
		}

		List<ReportVisitsPerProduct> reportVisitsPerProducts = reportProductDao.getVisitsPerProduct(employee,
				DateUtilities.convertToDate(reportVisitsPerProductInputDTO.getDateFrom(),
						DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(reportVisitsPerProductInputDTO.getDateTo(),
						DateUtilities.DATE_FORMATE_PATTERN));

		List reportVisitsPerProductOutputDTOs = new ArrayList();

		for (Iterator iterator = reportVisitsPerProducts.iterator(); iterator.hasNext();) {
			ReportVisitsPerProduct reportVisitsPerProduct = (ReportVisitsPerProduct) iterator.next();

			ReportVisitsPerProductOutputDTO reportVisitsPerProductOutputDTO = new ReportVisitsPerProductOutputDTO();

			mapper.map(reportVisitsPerProduct, reportVisitsPerProductOutputDTO);

			reportVisitsPerProductOutputDTOs.add(reportVisitsPerProductOutputDTO);
		}

		return reportVisitsPerProductOutputDTOs;
	}

	@Override
	public List visitsPerProductDetails(String userName,
			@Valid ReportVisitsPerProductDetailsInputDTO reportVisitsPerProductDetailsInputDTO)
			throws ReportVisitsPerProductServiceExcepion, ParseException {
		
		Employee employee = null;

		try {
			employee = getEmployee(userName);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitsPerProductServiceExcepion(e.getErrorCode());
		}
		
		List VisitsPerProductDetailsOutputDTOs = new ArrayList();
		
		List<Visit> visits = reportProductDao.getVisitsPerProductDetails(employee, reportVisitsPerProductDetailsInputDTO.getProductId());
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			
			VisitsPerProductDetailsOutputDTO visitDetailOutputDTO = new VisitsPerProductDetailsOutputDTO(visit.getId(), visit.getEmployeeByEmployeeId().getName()
				, visit.getEmployeeByEmployeeId().getManager().getName() 
				, DateUtilities.dateFormate(visit.getDatetime()), visit.getServiceProvider().getFirstName() + " " + visit.getServiceProvider().getLastName()
				, visit.getServiceProvider().getSpecialty().getName(), visit.getServiceProvider().getDistrict().getEnName());
			
			VisitsPerProductDetailsOutputDTOs.add(visitDetailOutputDTO);
		}
		
		return VisitsPerProductDetailsOutputDTOs;
	}

}
