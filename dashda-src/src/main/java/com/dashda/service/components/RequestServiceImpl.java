/**
 * 
 */
package com.dashda.service.components;

import java.net.Authenticator.RequestorType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.InputRequestRejectDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.InputRequestApprovalDTO;
import com.dashda.controllers.dto.PendingApprovalRequestInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.OffVisit;
import com.dashda.data.entities.OffVisitStatus;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.OffVisitDao;
import com.dashda.data.repositories.ScheduleDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.enums.OffVisitStatusEnum;
import com.dashda.enums.RequestTypeEnum;
import com.dashda.enums.ScheduleStatusEnum;
import com.dashda.exception.ApprovalServiceException;
import com.dashda.exception.ScheduleExceptionManager;

/**
 * @author mhanafy
 *
 */
@Service
public class RequestServiceImpl extends ServicesManager implements RequestService {

	@Autowired
	ScheduleDao scheduleDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OffVisitDao offVisitDao;
	
	@Autowired
	VisitDao visitDao;
	
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
			
			pendingApprovalRequestDTO.setServiceProviderId(schedule.getServiceProvider().getId());
			pendingApprovalRequestDTO
				.setServiceProviderTypeId(schedule.getServiceProvider().getServiceProviderType().getId());
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

	@Override
	public AppResponse approveRequest(String username, InputRequestApprovalDTO inputRequestApprovalDTO)  
				throws ApprovalServiceException{
	
			User user = userDao.findUserByUsername(username);
			String returnMessage = null;
			
			if(inputRequestApprovalDTO.getRequestTypeId() == RequestTypeEnum.SCHEDULE.getValue()) {
				returnMessage = approveScheduleItem(user, inputRequestApprovalDTO);
			}else if(inputRequestApprovalDTO.getRequestTypeId() == RequestTypeEnum.OFF_VISIT.getValue()) {
				returnMessage = approveOffVisit(user, inputRequestApprovalDTO);
			}
			
			return emptyResponse(returnMessage);
	}

	
	
	
	private String approveScheduleItem(User user, InputRequestApprovalDTO inputRequestApprovalDTO) 
						throws ApprovalServiceException {
		
		int scheduleItemId = inputRequestApprovalDTO.getRequestId();
		
			Employee employee = user.getEmployee();
			
			if(employee == null)
				throw new ApprovalServiceException(ERROR_CODE_1001);
			
			Schedule schedule = scheduleDao.findPendingApprovalScheduleForManagerByID
													(scheduleItemId, employee.getId());
			
			if(schedule == null)
				throw new ApprovalServiceException(ERROR_CODE_1003);
			
			schedule.setScheduleStatus(new ScheduleStatus(ScheduleStatusEnum.APPROVED.getValue()));
			
			Visit visit = new Visit();
			
			visit.setServiceProvider(schedule.getServiceProvider());
			visit.setDatetime(schedule.getDatetime());
			visit.setEmployeeByEmployeeId(schedule.getEmployeeByEmployeeId());
			//visit.setEmployeeBySubordinateId(schedule.getEmployeeBySubordinateId());
			
			visitDao.addVisit(visit);
			if(inputRequestApprovalDTO.getDoubleVisit() == 1) {
				Visit dubleVisit = new Visit();
				dubleVisit.setServiceProvider(schedule.getServiceProvider());
				dubleVisit.setDatetime(schedule.getDatetime());
				dubleVisit.setEmployeeByEmployeeId(employee);
				dubleVisit.setEmployeeBySubordinateId(schedule.getEmployeeByEmployeeId());
				visitDao.addVisit(dubleVisit);
			}
		
		scheduleDao.update(schedule);

		return "Schedule Approved";
	}

	private String approveOffVisit(User user, InputRequestApprovalDTO inputRequestApprovalDTO)
			throws ApprovalServiceException {
		
		OffVisit offVisit = offVisitDao.findOffVisit(inputRequestApprovalDTO.getRequestId());
		if(offVisit == null)
			throw new ApprovalServiceException(ERROR_CODE_1020);
		
		offVisit.setOffVisitStatus(new OffVisitStatus(OffVisitStatusEnum.ACCEPTED.getValue()));
		
		offVisitDao.approvOffVisitRequest(offVisit);
		return "Off Visit Approved";
	}

	@Override
	public AppResponse rejectRequest(String username, InputRequestRejectDTO inputRequestRejectDTO)
			throws ApprovalServiceException {
		
		User user = userDao.findUserByUsername(username);
		String returnMessage = null;
		
		if(inputRequestRejectDTO.getRequestTypeId() == RequestTypeEnum.SCHEDULE.getValue()) {
			returnMessage = rejectScheduleItem(user, inputRequestRejectDTO);
		}else if(inputRequestRejectDTO.getRequestTypeId() == RequestTypeEnum.OFF_VISIT.getValue()) {
			returnMessage = rejectOffVisit(user, inputRequestRejectDTO);
		}
		
		return emptyResponse(returnMessage);
	}

	
	private String rejectOffVisit(User user, InputRequestRejectDTO inputRequestRejectDTO)
					throws ApprovalServiceException {
		
		OffVisit offVisit = offVisitDao.findOffVisit(inputRequestRejectDTO.getRequestId());
		if(offVisit == null)
			throw new ApprovalServiceException(ERROR_CODE_1020);
		
		offVisit.setOffVisitStatus(new OffVisitStatus(OffVisitStatusEnum.REJECTED.getValue()));
		
		offVisitDao.approvOffVisitRequest(offVisit);
		return "Off Visit Item Rejected";
	}

	private String rejectScheduleItem(User user, InputRequestRejectDTO inputRequestRejectDTO) 
			throws ApprovalServiceException {
		
		int scheduleItemId = inputRequestRejectDTO.getRequestId();
		
		Employee employee = user.getEmployee();
		
		if(employee == null)
			throw new ApprovalServiceException(ERROR_CODE_1001);
		
		Schedule schedule = scheduleDao.findPendingApprovalScheduleForManagerByID
				(scheduleItemId, employee.getId());
		if(schedule == null)
			throw new ApprovalServiceException(ERROR_CODE_1020);
		
		schedule.setScheduleStatus(new ScheduleStatus(ScheduleStatusEnum.REJECTED.getValue()));
		scheduleDao.update(schedule);
		
		return "Schedule Item Rejected";
	}

}
