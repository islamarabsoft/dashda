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

import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.SchedulingException;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.data.entities.Doctor;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeDoctor;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.EmployeeDoctorDao;
import com.dashda.data.repositories.ScheduleDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.enums.ScheduleStatusEnum;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.utilities.CompareDateWithoutTime;
import com.dashda.utilities.DateValidator;

import ch.qos.logback.core.spi.ScanException;

/**
 * @author mhanafy
 *
 */

@Service("scheduleService")
@PropertySource("classpath:exception-messages.properties")
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
	
	private Schedule schedule;
	
	private User user;

	private List<Schedule> schedules;

	private ScheduleDTO scheduleDTO;

	private List<ScheduleDTO> scheduleDTOs;
	
	private Visit visit;
	
	private Doctor doctor;

	private Employee employee;

	private EmployeeDoctor employeeDoctor;
	
	@Override
	public void addScheduleItem(String username, ScheduleDTO scheduleDTO) throws ParseException, ScheduleExceptionManager  {
			
		user = userDao.findUserByUsername(username);
		
		Date scheduleDate = new SimpleDateFormat("dd/MM/yyyy").parse(scheduleDTO.getScheduleDate());
		
		employee = user.getEmployee();
		
		if(employee == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1006) ;
		if(employee.getManager() == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1007) ;
		
		if(!DateValidator.isThisDateValid(scheduleDTO.getScheduleDate(), "dd/MM/yyyy"))
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
		
		scheduleDao.addScheduleItem(schedule);
	}

	@Override
	public void approveScheduleItems(String username, List<Integer> scheduleItems) throws ScheduleExceptionManager {
		this.updateScheduleStatus(username, scheduleItems, 2);

	}

	@Override
	public void rejectScheduleItems(String username, List<Integer> scheduleItems) throws ScheduleExceptionManager {
		this.updateScheduleStatus(username, scheduleItems, 3);
		
	}
	
	@Override
	public void approveSchedule(String username, int employeeId) {
		this.updateScheduleStatus(employeeId, 2);
	}
	
	@Override
	public void rejectSchedule(String username, int employeeId) {
		this.updateScheduleStatus(employeeId, 3);
	}
	
	@Override
	public List<ScheduleDTO> scheduleItemsListNeedAttention(String username) throws ScheduleExceptionManager {
		
		user = userDao.findUserByUsername(username);
		employee = user.getEmployee();
		if(employee == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1001);
		System.out.println("Employee ID : " + employee.getId());
		schedules = scheduleDao.findListofScheduleItemsNeedAttention(employee.getId());
		
		scheduleDTOs = new ArrayList<ScheduleDTO>();
		
		for (Iterator<Schedule> iterator = schedules.iterator(); iterator.hasNext();) {
			Schedule schedule = (Schedule) iterator.next();
			
			scheduleDTO = new ScheduleDTO();
			
			scheduleDTO.setScheduleId(schedule.getId());
			scheduleDTO.setDoctorId(schedule.getDoctor().getId()+"");
			scheduleDTO.setScheduleDate(schedule.getDatetime()+"");
			scheduleDTO.setEmployeeId(schedule.getEmployeeByEmployeeId().getId());
			scheduleDTO.setEmployeeName(schedule.getEmployeeByEmployeeId().getContact().getFirstName() + " " 
												+ schedule.getEmployeeByEmployeeId().getContact().getFirstName());
			
			scheduleDTOs.add(scheduleDTO);
		}
		
		return scheduleDTOs;
	}
	

	
	private void updateScheduleStatus(String username, List<Integer> scheduleItems, int scheduleStatus) throws ScheduleExceptionManager {
		for (Iterator<Integer> iterator = scheduleItems.iterator(); iterator.hasNext();) {
			Integer scheduleItem = (Integer) iterator.next();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}



}
