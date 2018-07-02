/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ReportTargetVisitInputDTO;
import com.dashda.controllers.dto.ReportTargetVisitOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.ReportDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.ReportVisitServiceException;

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
			String visitsCount = "Two Visits";
			
			if(serviceProvider.getServiceProviderGrade().getId() == 2)
				visitsCount = "One Visits";
			
			ReportTargetVisitOutputDTO reportTargetVisitOutputDTO = new ReportTargetVisitOutputDTO(serviceProvider.getFirstName(), 
					serviceProvider.getLastName(), serviceProvider.getSpecialty().getName(), serviceProvider.getDistrict().getEnName(),
					visitsCount);
			
			reportTargetVisitOutputDTOs.add(reportTargetVisitOutputDTO);
		}
		
		

		return okListResponse(reportTargetVisitOutputDTOs);
	}

}
