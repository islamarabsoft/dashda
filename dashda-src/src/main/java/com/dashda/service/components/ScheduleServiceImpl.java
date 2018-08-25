/**
 * 
 */
package com.dashda.service.components;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AddScheduleInputDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ModifyScheduleInputDTO;
import com.dashda.controllers.dto.ScheduleActionDTO;
import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.controllers.dto.ScheduleListInputDTO;
import com.dashda.controllers.dto.employee.EmployeeSchedulesDTO;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeServiceProvider;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.VisitDao;
import com.dashda.data.repositories.employee.EmployeeDao;
import com.dashda.data.repositories.employee.EmployeeServiceProviderDao;
import com.dashda.data.repositories.plan.ScheduleDao;
import com.dashda.data.repositories.serviceProvider.DoctorDao;
import com.dashda.data.repositories.user.UserDao;
import com.dashda.enums.ScheduleStatusEnum;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.utilities.DateUtilities;

/**
 * @author mhanafy
 *
 */

@Service("scheduleService")
public class ScheduleServiceImpl extends ServicesManager implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private VisitDao visitDao;
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private EmployeeServiceProviderDao employeeDoctorDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private Schedule schedule;
	
	private User user;

	private List<Schedule> schedules;

	private ScheduleDTO scheduleDTO;

	private List scheduleDTOs;
	
	private Visit visit;
	
	private ServiceProvider doctor;

	private Employee employee;

	private EmployeeServiceProvider employeeDoctor;
	
	@Override
	public AppResponse addScheduleItem(String username, AddScheduleInputDTO addScheduleInputDTO) throws ParseException, ScheduleExceptionManager  {
			
		user = userDao.findUserByUsername(username);
		
		Date scheduleDate = new SimpleDateFormat(DateUtilities.DATE_FORMATE_PATTERN).
											parse(addScheduleInputDTO.getScheduleDate());
		
		employee = user.getEmployee();
		
		if(employee == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1006) ;
		if(employee.getManager() == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1007) ;
		
		if(!DateUtilities.isThisDateValid(addScheduleInputDTO.getScheduleDate()))
			throw new ScheduleExceptionManager(ERROR_CODE_1012);

		if(DateUtilities.compareTwoDates(new Date(), scheduleDate) == -1)
			throw new ScheduleExceptionManager(ERROR_CODE_1013);
        	
		
		doctor = doctorDao.findDoctorById(addScheduleInputDTO.getServiceProviderId());
		if(doctor == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1011);
		
		employeeDoctor = employeeDoctorDao.findEmployeeServiceProviderByEmployeeIdAndServiceProviderId
											(employee.getId(), doctor.getId());
		
		if (employeeDoctor == null) 
			throw new ScheduleExceptionManager(ERROR_CODE_1005);
		
		schedule = new Schedule();
		
		schedule.setServiceProvider(doctor);
		schedule.setEmployeeByEmployeeId(employee);
		schedule.setEmployeeByManagerId(employee.getManager());
		schedule.setDatetime(scheduleDate);
		schedule.setScheduleStatus(new ScheduleStatus(ScheduleStatusEnum.PENDING_APPROVAL.getValue()));
		
		scheduleDao.saveScheduleItem(schedule);
		scheduleDTO = new ScheduleDTO();
		scheduleDTO.setScheduleId(schedule.getId());
		scheduleDTO.setEmployeeId(schedule.getEmployeeByEmployeeId().getId()+"");
		
		return createResponse(scheduleDTO, "Schedule Item Added Successfully");
	}

	@Override
	public AppResponse approveScheduleItems(String username, ScheduleActionDTO scheduleActionDTO) throws ScheduleExceptionManager {
		this.updateScheduleStatus(username, scheduleActionDTO, ScheduleStatusEnum.APPROVED.getValue());
		return emptyResponse("Schedule Approved");

	}

	@Override
	public AppResponse discardScheduleItems(String username, ScheduleActionDTO scheduleActionDTO) throws ScheduleExceptionManager {
		this.updateScheduleStatus(username, scheduleActionDTO, ScheduleStatusEnum.REJECTED.getValue());
		return emptyResponse("Schedule Rejected");
		
	}
	
	@Override
	public AppResponse scheduleItemsListNeedAttention(String username, ScheduleListInputDTO scheduleListInputDTO) 
				throws ScheduleExceptionManager, ParseException {
		
		user = userDao.findUserByUsername(username);
		employee = user.getEmployee();
		if(employee == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1001);
		schedules = scheduleDao.findListofScheduleItemsNeedAttention(employee.getId());

		
		EmployeeSchedulesDTO employeeSchedulesDTO = null;
		List employeeSchedulesDTOs = new ArrayList();;
		scheduleDTOs = new ArrayList();
		
		
				
		for (Iterator scheduleIt = schedules.iterator(); scheduleIt.hasNext();) {
			
			Schedule schedule = (Schedule) scheduleIt.next();
			boolean inList = false;
			
			for (Iterator iterator = employeeSchedulesDTOs.iterator(); iterator.hasNext();) {
				EmployeeSchedulesDTO employeeSchedulesDTO2 = (EmployeeSchedulesDTO) iterator.next();
				if(employeeSchedulesDTO2.getEmployeeId().equals(schedule.getEmployeeByEmployeeId().getId()+""))
					inList = true;
			}
			
			if(!inList) {
				employeeSchedulesDTO = new EmployeeSchedulesDTO();
				employeeSchedulesDTO.setEmployeeId(schedule.getEmployeeByEmployeeId().getId()+"");
				employeeSchedulesDTO.setEmployeeName(schedule.getEmployeeByEmployeeId().getContact().getFirstName() 
						+" "+ schedule.getEmployeeByEmployeeId().getContact().getLastName());
				scheduleDTOs = new ArrayList();
				
				for (Iterator scheduleItemInsideEmployeeIt = schedule.getEmployeeByEmployeeId().getSchedulesForEmployeeId().iterator(); 
						scheduleItemInsideEmployeeIt.hasNext();) {
					Schedule scheduleItemInsideEmployee = (Schedule) scheduleItemInsideEmployeeIt.next();
					
					boolean inServiceProviderTypeList = false;
					if(scheduleListInputDTO.getServiceTypeId() == 0)
						inServiceProviderTypeList = true;
					else
						inServiceProviderTypeList = scheduleItemInsideEmployee.getServiceProvider()
							.getServiceProviderType().getId() == scheduleListInputDTO.getServiceTypeId();
					
					if(scheduleItemInsideEmployee.getScheduleStatus().getId() == 1 &&
							inServiceProviderTypeList) {
						
						scheduleDTO = new ScheduleDTO();
						scheduleDTO.setScheduleId(scheduleItemInsideEmployee.getId());
						scheduleDTO.setServiceProviderId(scheduleItemInsideEmployee.getServiceProvider().getId()+"");
						scheduleDTO.setServiceProviderName(scheduleItemInsideEmployee.getServiceProvider().getEnName());
						scheduleDTO.setEmployeeId(employeeSchedulesDTO.getEmployeeId());
						scheduleDTO.setEmployeeName(employeeSchedulesDTO.getEmployeeName());
						scheduleDTO.setScheduleDate(DateUtilities.dateFormate(scheduleItemInsideEmployee.getDatetime()));
						scheduleDTOs.add(scheduleDTO);
					}
				}
				employeeSchedulesDTO.setSchedules(scheduleDTOs);
				employeeSchedulesDTOs.add(employeeSchedulesDTO);
				inList = false;
			}
		}
		
		return okListResponse(employeeSchedulesDTOs, "List Size: " +scheduleDTOs.size());
	}
	

	
	private void updateScheduleStatus(String username, ScheduleActionDTO scheduleActionDTO, int scheduleStatus) throws ScheduleExceptionManager {
		List<Integer> scheduleDTOs = scheduleActionDTO.getScheduleDTOs();
		
		for (Iterator<Integer> iterator = scheduleDTOs.iterator(); iterator.hasNext();) {
			Integer scheduleItem =  iterator.next();
			user = userDao.findUserByUsername(username);
			employee = user.getEmployee();
			
			if(employee == null)
				throw new ScheduleExceptionManager(ERROR_CODE_1001);
			
			schedule = scheduleDao.findPendingApprovalScheduleForManagerByID(scheduleItem, employee.getId());
			
			if(schedule == null)
				throw new ScheduleExceptionManager(ERROR_CODE_1003);
			
			schedule.setScheduleStatus(new ScheduleStatus(scheduleStatus));
			
			try {
				if(scheduleStatus == ScheduleStatusEnum.APPROVED.getValue()) {
					visit = new Visit();
					
					visit.setServiceProvider(schedule.getServiceProvider());
					visit.setDatetime(schedule.getDatetime());
					visit.setEmployeeByEmployeeId(schedule.getEmployeeByEmployeeId());
					//visit.setEmployeeBySubordinateId(schedule.getEmployeeBySubordinateId());
					
					visitDao.addVisit(visit);
					if(scheduleActionDTO.getDubleVisit().equalsIgnoreCase("1")) {
						Visit dubleVisit = new Visit();
						dubleVisit.setServiceProvider(schedule.getServiceProvider());
						dubleVisit.setDatetime(schedule.getDatetime());
						dubleVisit.setEmployeeByEmployeeId(employee);
						dubleVisit.setEmployeeBySubordinateId(schedule.getEmployeeByEmployeeId());
						visitDao.addVisit(dubleVisit);
					}
				}
				scheduleDao.update(schedule);
				
			} catch (Exception e) {
				log.error("Visit Error ::: ", e);
			}
			
		}
	}
	

	private void updateScheduleStatus(int employeeId, int scheduleStatus) {
		
		schedules = scheduleDao.findListofScheduleItemsPendingApprovalByEmployee(employeeId);
		
		for (Schedule schedule : schedules) {
			
			schedule.setScheduleStatus(new ScheduleStatus(scheduleStatus));
			
			try {
				if(scheduleStatus == ScheduleStatusEnum.APPROVED.getValue()) {
					visit = new Visit();
					
					visit.setServiceProvider(schedule.getServiceProvider());
					visit.setDatetime(schedule.getDatetime());
					visit.setEmployeeByEmployeeId(schedule.getEmployeeByEmployeeId());
					visit.setEmployeeBySubordinateId(schedule.getEmployeeBySubordinateId());
					
					visitDao.addVisit(visit);
				}				
				scheduleDao.update(schedule);
				
			} catch (Exception e) {
				log.error("Visit Error ::: ", e);
			}
		}
		
	}


	@Override
	public AppResponse modifyScheduleItemData(ModifyScheduleInputDTO modifyScheduleInputDTO) 
			throws ScheduleExceptionManager, ParseException{
		
		
		
		schedule = scheduleDao.findScheduleByID(modifyScheduleInputDTO.getScheduleId());
		
		if(schedule == null) 
			throw new ScheduleExceptionManager(ERROR_CODE_1003);
		
		if(!DateUtilities.isThisDateValid(modifyScheduleInputDTO.getScheduleDate()))
			throw new ScheduleExceptionManager(ERROR_CODE_1012);
		
		Date date = new SimpleDateFormat(DateUtilities.DATE_FORMATE_PATTERN).parse(modifyScheduleInputDTO.getScheduleDate());
		
		if(DateUtilities.compareTwoDates(new Date(), date) == -1)
			throw new ScheduleExceptionManager(ERROR_CODE_1013);
		
		schedule.setDatetime(date);
		
		scheduleDao.saveScheduleItem(schedule);
	
		scheduleDTO = new ScheduleDTO(modifyScheduleInputDTO.getScheduleId(), schedule.getServiceProvider().getId()+"", 
						DateUtilities.dateFormate(schedule.getDatetime())+"", schedule.getEmployeeByEmployeeId().getId()+"");
		
		
		return okResponse(scheduleDTO, "Schedule Item Updated Successfully");
	}

	@Override
	public AppResponse removeScheduleItem(int scheduleId) throws ScheduleExceptionManager {
		schedule = scheduleDao.findScheduleByID(scheduleId);
		if(schedule == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1003);
		
		scheduleDao.deleteScheduleItem(schedule);
		
		return emptyResponse("Object Deleted Successfully {"+scheduleId+"}");
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public AppResponse approveSchedule(String username, int employeeId) {
//		this.updateScheduleStatus(employeeId, ScheduleStatusEnum.APPROVED.getValue());
//		return emptyResponse("Schedule Approved");
//	}
//	
//	@Override
//	public AppResponse rejectSchedule(String username, int employeeId) {
//		this.updateScheduleStatus(employeeId, ScheduleStatusEnum.REJECTED.getValue());
//		return emptyResponse("Schedule Rejected");
//	}

}
