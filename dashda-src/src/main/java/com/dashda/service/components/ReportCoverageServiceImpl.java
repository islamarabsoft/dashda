
/**
 * 
 */
package com.dashda.service.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ReportCoverageOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportCoverageEntity;
import com.dashda.data.repositories.ReportCoverageDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.ReportCoverageServiceException;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportCoverageServiceImpl extends ServicesManager implements ReportCoverageService {

	@Autowired
	ReportCoverageDao reportCoverageDao;
	
	@Override
	public AppResponse userCoverage(String username) throws ReportCoverageServiceException {
		
		
		Employee employee = null;
		
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportCoverageServiceException(e.getMessage());
		}
		
		ReportCoverageEntity reportCoverageEntity = reportCoverageDao.coverage(employee.getId());
		ReportCoverageOutputDTO reportCoverageOutputDTO = new ReportCoverageOutputDTO(reportCoverageEntity.getListCount(), reportCoverageEntity.getVisitsCount(), 
				(reportCoverageEntity.getVisitsCount()/reportCoverageEntity.getListCount())*100);
		return okResponse(reportCoverageOutputDTO, "Report Generated Successfully");
	}

}
