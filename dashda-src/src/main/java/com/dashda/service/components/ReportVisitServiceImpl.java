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

import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ReportTargetVisitInputDTO;
import com.dashda.controllers.dto.ReportTargetVisitOutputDTO;
import com.dashda.controllers.dto.ReportUnVisitInputDTO;
import com.dashda.controllers.dto.ReportUnVisitOutputDTO;
import com.dashda.controllers.dto.ReportVisitsPerEmployeeOutputDTO;
import com.dashda.controllers.dto.VisitDetailInputDTO;
import com.dashda.controllers.dto.VisitsListInputDTO;
import com.dashda.controllers.dto.VisitsPerEmployeeInputDTO;
import com.dashda.data.entities.DoubleVisit;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ProductVisit;
import com.dashda.data.entities.ReportUnVisit;
import com.dashda.data.entities.ReportVisitsPerEmployee;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.ReportDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.ReportVisitServiceException;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportVisitServiceImpl extends ServicesManager implements ReportVisitService {

	@Autowired
	ReportDao reportDao;
	
	@Override
	public AppResponse getTargetVisits(String username, ReportTargetVisitInputDTO reportTargetVisitInputDTO)
			throws ReportVisitServiceException {
		Employee employee;
		
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitServiceException(e.getErrorCode());
		}
		
		int employeeId = 0;
		if(reportTargetVisitInputDTO.getEmployeeId() == 0)
			employeeId = employee.getId();
		else
			employeeId = reportTargetVisitInputDTO.getEmployeeId();
		
		List reportTargetVisitOutputDTOs = new ArrayList();
		List<ServiceProvider> serviceProviders = reportDao.generateTargetVisits(employeeId);
		
		for (Iterator iterator = serviceProviders.iterator(); iterator.hasNext();) {
			ServiceProvider serviceProvider = (ServiceProvider) iterator.next();
			String visitsCount = "2";
			
			if(serviceProvider.getServiceProviderGrade().getId() == 2)
				visitsCount = "1";
			
			ReportTargetVisitOutputDTO reportTargetVisitOutputDTO = new ReportTargetVisitOutputDTO(serviceProvider.getFirstName(), 
					serviceProvider.getLastName(), serviceProvider.getSpecialty().getName(), serviceProvider.getDistrict().getEnName(),
					visitsCount);
			
			reportTargetVisitOutputDTOs.add(reportTargetVisitOutputDTO);
		}
		
		

		return okListResponse(reportTargetVisitOutputDTOs);
	}

	@Override
	public AppResponse getUnVisits(String username, ReportUnVisitInputDTO reportUntVisitInputDTO)
			throws ReportVisitServiceException {
		Employee employee;
		
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitServiceException(e.getErrorCode());
		}
		
		int employeeId = 0;
		if(reportUntVisitInputDTO.getEmployeeId() == 0)
			employeeId = employee.getId();
		else
			employeeId = reportUntVisitInputDTO.getEmployeeId();
		
		List reportUnVisitOutputDTOs = new ArrayList();
		
		
		List<ReportUnVisit> serviceProviders = reportDao.generateUnVisits(employeeId);
		
		for (Iterator iterator = serviceProviders.iterator(); iterator.hasNext();) {
			ReportUnVisit reportUnVisit = (ReportUnVisit) iterator.next();
			ReportUnVisitOutputDTO reportUnVisitOutputDTO = new ReportUnVisitOutputDTO();
			
			mapper.map(reportUnVisit, reportUnVisitOutputDTO);
			
			reportUnVisitOutputDTOs.add(reportUnVisitOutputDTO);
		}
		
		return okListResponse(reportUnVisitOutputDTOs);
	}

	@Override
	public AppResponse getVisitsList(String username, VisitsListInputDTO visitsListInputDTO) throws ReportVisitServiceException, ParseException {
	
		Employee employee;
		
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitServiceException(e.getErrorCode());
		}
		
		int employeeId = 0;
		if(visitsListInputDTO.getEmployeeId() == 0)
			employeeId = employee.getId();
		else
			employeeId = visitsListInputDTO.getEmployeeId();
		
		List reportUnVisitOutputDTOs = new ArrayList();
		List<Visit> visits = reportDao.getVisitsList(employeeId, DateUtilities.convertToDate(visitsListInputDTO.getDateFrom(), DateUtilities.DATE_FORMATE_PATTERN)
				, DateUtilities.convertToDate(visitsListInputDTO.getDateTo(), DateUtilities.DATE_FORMATE_PATTERN));
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			
			VisitsListDetailOutputDTO visitDetailOutputDTO = new VisitsListDetailOutputDTO(visit.getId(), visit.getEmployeeByEmployeeId().getManager().getName() 
				, DateUtilities.dateFormate(visit.getDatetime()), visit.getServiceProvider().getFirstName() + " " + visit.getServiceProvider().getLastName()
				, visit.getServiceProvider().getSpecialty().getName(), visit.getServiceProvider().getDistrict().getEnName());
			
			reportUnVisitOutputDTOs.add(visitDetailOutputDTO);
		}
		
		return okListResponse(reportUnVisitOutputDTOs);
	}

	@Override
	public AppResponse getVisitDetail(String username, @Valid VisitDetailInputDTO visitDetailInputDTO) throws ReportVisitServiceException, ParseException{
		
		Visit visit = reportDao.getvisitDetail(visitDetailInputDTO.getVisitId());
		VisitDetailOutpoutDTO visitDetailOutpoutDTO = new VisitDetailOutpoutDTO();
		
		ServiceProvider provider = visit.getServiceProvider();
		visitDetailOutpoutDTO.setBrick(provider.getDistrict().getEnName());
		visitDetailOutpoutDTO.setServiceProvider(getServiceProviderName(provider));
		visitDetailOutpoutDTO.setServiceProviderType(getServiceProviderType(provider));
		visitDetailOutpoutDTO.setSpecialty(provider.getSpecialty().getName());
		visitDetailOutpoutDTO.setDatetime(DateUtilities.dateFormate(visit.getDatetime()));
		visitDetailOutpoutDTO.setComment(visit.getComment());
		visitDetailOutpoutDTO.setIsDoubleVisit(visit.getDoubleVisit()+"");
		
		List<String> products = new ArrayList<String>();
		List<String> managers = new ArrayList<String>();
		
		for (Iterator iterator = visit.getProductVisits().iterator(); iterator.hasNext();) {
			ProductVisit productVisit = (ProductVisit) iterator.next();
			products.add(productVisit.getProduct().getName());
			
		}
		visitDetailOutpoutDTO.setProducts(products);
		
		if(visit.getDoubleVisit() == 1) {
			List<DoubleVisit> doubleVisits = visit.getDoubleVisits();
			for (Iterator iterator = doubleVisits.iterator(); iterator.hasNext();) {
				DoubleVisit doubleVisit = (DoubleVisit) iterator.next();
				
				managers.add(doubleVisit.getManager().getName());
			}	
		}
		visitDetailOutpoutDTO.setManagers(managers);
		
		
		
		
		return okResponse(visitDetailOutpoutDTO, "Object fulfilled successfully");
	}



	@Override
	public AppResponse visitsPerEmployee(String username, @Valid VisitsPerEmployeeInputDTO visitsPerEmployeeInputDTO) throws ReportVisitServiceException {
		
		Employee employee;
		
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportVisitServiceException(e.getErrorCode());
		}
		
		
		
		List<ReportVisitsPerEmployee> visitsPerEmployees = reportDao.generateVisitsPerEmployee(employee.getId());
		List visitsPerEmployeeDTOs = new ArrayList<ReportVisitsPerEmployeeOutputDTO>();
		
		for (Iterator iterator = visitsPerEmployees.iterator(); iterator.hasNext();) {
			ReportVisitsPerEmployee reportVisitsPerEmployee = (ReportVisitsPerEmployee) iterator.next();
			ReportVisitsPerEmployeeOutputDTO reportVisitsPerEmployeeOutputDTO = new ReportVisitsPerEmployeeOutputDTO();
			
			mapper.map(reportVisitsPerEmployee, reportVisitsPerEmployeeOutputDTO);
			visitsPerEmployeeDTOs.add(reportVisitsPerEmployeeOutputDTO);
		}
		
		return okListResponse(visitsPerEmployeeDTOs);
	}

}
