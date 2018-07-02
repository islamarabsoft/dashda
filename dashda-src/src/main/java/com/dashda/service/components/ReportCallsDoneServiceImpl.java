/**
 * 
 */
package com.dashda.service.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ReportCallsDoneOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportCallsDone;
import com.dashda.data.repositories.ReportCoverageDao;
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

		ReportCallsDoneOutputDTO reportCallsDoneOutputDTO = new ReportCallsDoneOutputDTO(reportCallsDone.getTarget(), 
				reportCallsDone.getActual(), (reportCallsDone.getActual()/reportCallsDone.getTarget())*100);

		return okResponse(reportCallsDoneOutputDTO, "Report Generated Successfully");
	}

}
