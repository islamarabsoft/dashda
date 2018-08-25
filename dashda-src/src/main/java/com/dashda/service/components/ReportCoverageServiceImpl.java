
/**
 * 
 */
package com.dashda.service.components;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.report.ReportCoverageOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportCoverageEntity;
import com.dashda.data.repositories.report.ReportCoverageDao;
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
		
		String percentage = "0";
		float floatPercentage = 0.0f;
		if(reportCoverageEntity.getListCount() != 0) {
			float total = (float)reportCoverageEntity.getListCount();
			float actual = (float)reportCoverageEntity.getVisitsCount();
			floatPercentage = (actual/total)*100.f;
		}
			
		DecimalFormat f = new DecimalFormat("##.00");
		
		percentage = f.format(floatPercentage);
		
		ReportCoverageOutputDTO reportCoverageOutputDTO = new ReportCoverageOutputDTO(reportCoverageEntity.getListCount(), reportCoverageEntity.getVisitsCount(), 
				percentage);
		return okResponse(reportCoverageOutputDTO, "Report Generated Successfully");
	}

}
