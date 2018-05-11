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
import com.dashda.controllers.dto.PendingApprovalRequestInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.OffVisit;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.OffVisitDao;
import com.dashda.data.repositories.ScheduleDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.enums.RequestTypeEnum;
import com.dashda.exception.ApprovalServiceException;

/**
 * @author mhanafy
 *
 */
@Service
public class ApprovalServiceImpl extends ServicesManager implements ApprovalService {

	@Autowired
	ScheduleDao scheduleDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OffVisitDao offVisitDao;
	
	@Override
	public AppResponse getListOfPendingApprovalrequests(String username) throws ApprovalServiceException {
		PendingApprovalRequestInputDTO pendingApprovalRequestDTO;
		List pendingApprovalRequestDTOs = new ArrayList<PendingApprovalRequestInputDTO>();
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee(); 
		if (employee == null) 
			throw new ApprovalServiceException(ERROR_CODE_1001);

		//For Schedule List		
		List<Schedule> schedules = scheduleDao.findListofScheduleItemsNeedAttention(employee.getId());		

		for (Iterator iterator = schedules.iterator(); iterator.hasNext();) {
			Schedule schedule = (Schedule) iterator.next();
			
			pendingApprovalRequestDTO = new PendingApprovalRequestInputDTO();
			pendingApprovalRequestDTO.setEmployeeId(employee.getId()+"");
			pendingApprovalRequestDTO.setEmployeeName(employee.getContact().getFirstName());
			
			pendingApprovalRequestDTO.setRequestId(schedule.getId());
			pendingApprovalRequestDTO.setRequestTypeId(RequestTypeEnum.SCHEDULE.getValue()+"");
			pendingApprovalRequestDTO.setRequestDate(schedule.getDatetime()+"");
			
			pendingApprovalRequestDTO.setServiceProviderId(schedule.getServiceProvider().getId()+"");
			pendingApprovalRequestDTO.setServiceProviderName(schedule.getServiceProvider().getEnName());
			
			pendingApprovalRequestDTOs.add(pendingApprovalRequestDTO);
			
		}
		
		//Off Visits List
		List<OffVisit> offVisits = offVisitDao.findPendingApproval(employee.getId());
		
		for (Iterator iterator = offVisits.iterator(); iterator.hasNext();) {
			OffVisit offVisit = (OffVisit) iterator.next();
			
			pendingApprovalRequestDTO = new PendingApprovalRequestInputDTO();
			pendingApprovalRequestDTO.setEmployeeId(employee.getId()+"");
			pendingApprovalRequestDTO.setEmployeeName(employee.getContact().getFirstName());
			
			pendingApprovalRequestDTO.setRequestId(offVisit.getId());
			pendingApprovalRequestDTO.setRequestTypeId(RequestTypeEnum.OFF_VISIT.getValue()+"");
			pendingApprovalRequestDTO.setRequestDate(offVisit.getDateTime()+"");
			
			pendingApprovalRequestDTO.setReason(offVisit.getOffVisitReason().getName());
			pendingApprovalRequestDTO.setComment(offVisit.getComment());
			
			pendingApprovalRequestDTOs.add(pendingApprovalRequestDTO);
			
		}
		
		return okListResponse(pendingApprovalRequestDTOs, "List Size : " + pendingApprovalRequestDTOs.size());
	}

}
