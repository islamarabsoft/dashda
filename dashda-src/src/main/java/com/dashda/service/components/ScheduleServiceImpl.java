/**
 * 
 */
package com.dashda.service.components;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.EmployeeSchedulesDTO;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.ScheduleActionDTO;
import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.data.entities.Doctor;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeDoctor;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.EmployeeDao;
import com.dashda.data.repositories.EmployeeDoctorDao;
import com.dashda.data.repositories.ScheduleDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.enums.ScheduleStatusEnum;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.utilities.CompareDateWithoutTime;
import com.dashda.utilities.DateValidator;
import com.google.appengine.api.files.FileServicePb.CreateResponse;


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
	private EmployeeDoctorDao employeeDoctorDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private Schedule schedule;
	
	private User user;

	private List<Schedule> schedules;

	private ScheduleDTO scheduleDTO;

	private List scheduleDTOs;
	
	private Visit visit;
	
	private Doctor doctor;

	private Employee employee;

	private EmployeeDoctor employeeDoctor;
	
	@Override
	public AppResponse addScheduleItem(String username, ScheduleDTO scheduleDTO) throws ParseException, ScheduleExceptionManager  {
			
		user = userDao.findUserByUsername(username);
		
		Date scheduleDate = new SimpleDateFormat(DateValidator.DATE_FORMATE_PATTERN).parse(scheduleDTO.getScheduleDate());
		
		employee = user.getEmployee();
		
		if(employee == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1006) ;
		if(employee.getManager() == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1007) ;
		
		if(!DateValidator.isThisDateValid(scheduleDTO.getScheduleDate()))
			throw new ScheduleExceptionManager(ERROR_CODE_1012 + " '" + scheduleDTO.getScheduleDate()+ "'");

		if(CompareDateWithoutTime.compareTwoDates(new Date(), scheduleDate) == -1)
			throw new ScheduleExceptionManager(ERROR_CODE_1013 + " '" + scheduleDTO.getScheduleDate()+ "'");
        	
		
		doctor = doctorDao.findDoctorById(Integer.parseInt(scheduleDTO.getDoctorId()));
		if(doctor == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1011 +" '"+ scheduleDTO.getDoctorId() +"'");
		
		employeeDoctor = employeeDoctorDao.findEmployeeDoctorByEmployeeIdAndDoctorId(employee.getId(), doctor.getId());
		
		if (employeeDoctor == null) 
			throw new ScheduleExceptionManager(ERROR_CODE_1005);
		
		schedule = new Schedule();
		
		schedule.setDoctor(doctor);
		schedule.setEmployeeByEmployeeId(employee);
		schedule.setEmployeeByManagerId(employee.getManager());
		schedule.setDatetime(scheduleDate);
		schedule.setScheduleStatus(new ScheduleStatus(ScheduleStatusEnum.PENDING_APPROVAL.getValue()));
		
		scheduleDao.saveScheduleItem(schedule);
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
	public AppResponse rejectScheduleItems(String username, ScheduleActionDTO scheduleActionDTO) throws ScheduleExceptionManager {
		this.updateScheduleStatus(username, scheduleActionDTO, ScheduleStatusEnum.REJECTED.getValue());
		return emptyResponse("Schedule Rejected");
		
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
	
	@Override
	public AppResponse scheduleItemsListNeedAttention(String username) throws ScheduleExceptionManager, ParseException {
		
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
					
					if(scheduleItemInsideEmployee.getScheduleStatus().getId() == 1) {
						scheduleDTO = new ScheduleDTO();
						scheduleDTO.setScheduleId(scheduleItemInsideEmployee.getId());
						scheduleDTO.setDoctorId(scheduleItemInsideEmployee.getDoctor().getId()+"");
						scheduleDTO.setDoctorName(scheduleItemInsideEmployee.getDoctor().getDoctorName());
						scheduleDTO.setEmployeeId(employeeSchedulesDTO.getEmployeeId());
						scheduleDTO.setEmployeeName(employeeSchedulesDTO.getEmployeeName());
						scheduleDTO.setScheduleDate(DateValidator.dateFormate(scheduleItemInsideEmployee.getDatetime()));
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
					
					visit.setDoctor(schedule.getDoctor());
					visit.setDatetime(schedule.getDatetime());
					visit.setEmployeeByEmployeeId(schedule.getEmployeeByEmployeeId());
					visit.setEmployeeBySubordinateId(schedule.getEmployeeBySubordinateId());
					
					visitDao.addVisit(visit);
				}
				scheduleDao.update(schedule);
				
			} catch (SQLException e) {
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
					
					visit.setDoctor(schedule.getDoctor());
					visit.setDatetime(schedule.getDatetime());
					visit.setEmployeeByEmployeeId(schedule.getEmployeeByEmployeeId());
					visit.setEmployeeBySubordinateId(schedule.getEmployeeBySubordinateId());
					
					visitDao.addVisit(visit);
				}				
				scheduleDao.update(schedule);
				
			} catch (SQLException e) {
				log.error("Visit Error ::: ", e);
			}
		}
		
	}


	@Override
	public AppResponse modifyScheduleItemData(int scheduleId, String scheduleDate) throws ScheduleExceptionManager, ParseException{
		
		
		
		schedule = scheduleDao.findScheduleByID(scheduleId);
		
		if(schedule == null) 
			throw new ScheduleExceptionManager("ERROR_CODE - no schedule Item");
		
		if(!DateValidator.isThisDateValid(scheduleDate))
			throw new ScheduleExceptionManager(ERROR_CODE_1012 + " '" + scheduleDate + "'");
		
		Date date = new SimpleDateFormat(DateValidator.DATE_FORMATE_PATTERN).parse(scheduleDate);
		
		if(CompareDateWithoutTime.compareTwoDates(new Date(), date) == -1)
			throw new ScheduleExceptionManager(ERROR_CODE_1013 + " '" + scheduleDate + "'");
		
		schedule.setDatetime(date);
		
		scheduleDao.saveScheduleItem(schedule);
	
		scheduleDTO= new ScheduleDTO(scheduleId, schedule.getDoctor().getId()+"", 
						DateValidator.dateFormate(schedule.getDatetime())+"", schedule.getEmployeeByEmployeeId().getId()+"");
		
		
		return okResponse(scheduleDTO, "Schedule Item Updated Successfully");
	}

	@Override
	public AppResponse removeScheduleItem(int scheduleId) throws ScheduleExceptionManager {
		schedule = scheduleDao.findScheduleByID(scheduleId);
		if(schedule == null)
			throw new ScheduleExceptionManager("Can't Find Schedlue Item");
		
		scheduleDao.deleteScheduleItem(schedule);
		
		return emptyResponse("Object Deleted Successfully {"+scheduleId+"}");
	}

}
