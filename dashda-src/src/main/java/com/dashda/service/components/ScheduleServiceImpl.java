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
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.ScheduleDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.enums.ScheduleStatusEnum;
import com.dashda.exception.ScheduleExceptionManager;

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
	
	private Schedule schedule;
	
	private User user;

	private List<Schedule> schedules;

	private ScheduleDTO scheduleDTO;

	private List<ScheduleDTO> scheduleDTOs;
	
	private Visit visit;
	
	@Override
	public void addScheduleItem(String username, ScheduleDTO scheduleDTO) throws ParseException, ScheduleExceptionManager  {
		user = userDao.findUserByUsername(username);		
		
		//system validation
		if(scheduleDTO == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1003);
		if(scheduleDTO.getDoctorId() == 0)
			throw new ScheduleExceptionManager(ERROR_CODE_1004);
		if(scheduleDTO.getScheduleDate() == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1005);
		if(user.getEmployee() == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1006) ;
		if(user.getEmployee().getManager() == null)
			throw new ScheduleExceptionManager(ERROR_CODE_1007) ;
		
			schedule = new Schedule();
			
			schedule.setDoctor(new Doctor(scheduleDTO.getDoctorId()));
			schedule.setEmployeeByEmployeeId(user.getEmployee());
			schedule.setEmployeeByManagerId(user.getEmployee().getManager());
			schedule.setDatetime(new SimpleDateFormat("dd-MM-yyyy").parse(scheduleDTO.getScheduleDate()));
			schedule.setScheduleStatus(new ScheduleStatus(ScheduleStatusEnum.PENDING_APPROVAL.getValue()));
			
			scheduleDao.addScheduleItem(schedule);
	}

	@Override
	public void approveScheduleItems(String username, List<Integer> scheduleItems) {
		this.updateScheduleStatus(username, scheduleItems, 2);

	}

	@Override
	public void rejectScheduleItems(String username, List<Integer> scheduleItems) {
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
	public List<ScheduleDTO> scheduleItemsListNeedAttention(String username) {
		
		user = userDao.findUserByUsername(username);
		schedules = scheduleDao.findListofScheduleItemsNeedAttention(user.getEmployee().getId());
		
		scheduleDTOs = new ArrayList<ScheduleDTO>();
		
		for (Iterator<Schedule> iterator = schedules.iterator(); iterator.hasNext();) {
			Schedule schedule = (Schedule) iterator.next();
			
			scheduleDTO = new ScheduleDTO();
			
			scheduleDTO.setScheduleId(schedule.getId());
			scheduleDTO.setDoctorId(schedule.getDoctor().getId());
			scheduleDTO.setScheduleDate(schedule.getDatetime()+"");
			scheduleDTO.setEmployeeId(schedule.getEmployeeByEmployeeId().getId());
			scheduleDTO.setEmployeeName(schedule.getEmployeeByEmployeeId().getContact().getFirstName() + " " 
												+ schedule.getEmployeeByEmployeeId().getContact().getFirstName());
			
			scheduleDTOs.add(scheduleDTO);
		}
		
		return scheduleDTOs;
	}
	
	@Override
	public List<ScheduleDTO> schedulesNeedAttention(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private void updateScheduleStatus(String username, List<Integer> scheduleItems, int scheduleStatus) {
		for (Iterator<Integer> iterator = scheduleItems.iterator(); iterator.hasNext();) {
			Integer scheduleItem = (Integer) iterator.next();
			
			schedule = scheduleDao.findScheduleByID(scheduleItem);
			//TODO add business roles 
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
