/**
 * 
 */
package com.dashda.service.components;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.report.ReportCallsDoneOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportCallsDone;
import com.dashda.data.repositories.report.ReportCoverageDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.ReportCallsDoneServiceException;
import com.dashda.exception.ReportCoverageServiceException;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportCallsDoneServiceImpl extends ServicesManager implements ReportCallsDoneService {

	@Autowired
	ReportCoverageDao reportCoverageDao;
	@Override
	public AppResponse userCallsDone(String username) throws ReportCallsDoneServiceException {
		
		Employee employee = null;
		
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportCallsDoneServiceException(e.getMessage());
		}
		ReportCallsDone reportCallsDone = reportCoverageDao.callsDone(employee.getId());
		String percentage = "0";
		float floatPercentage = 0.0f;
		if(reportCallsDone.getTarget() != 0) {
			float total = (float)reportCallsDone.getTarget();
			float actual = (float)reportCallsDone.getActual();
			floatPercentage = (actual/total)*100.f;
		}
			
		DecimalFormat f = new DecimalFormat("##.00");
		
		percentage = f.format(floatPercentage);
		
		ReportCallsDoneOutputDTO reportCallsDoneOutputDTO = new ReportCallsDoneOutputDTO(reportCallsDone.getTarget(), 
				reportCallsDone.getActual(), percentage);

		return okResponse(reportCallsDoneOutputDTO, "Report Generated Successfully");
	}

}
