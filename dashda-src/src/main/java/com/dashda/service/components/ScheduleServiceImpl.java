/**
 * 
 */
package com.dashda.service.components;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.data.entities.Doctor;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.ScheduleDao;
import com.dashda.data.repositories.UserDao;

/**
 * @author mhanafy
 *
 */

@Service("scheduleService")
public class ScheduleServiceImpl extends ServicesManager implements ScheduleService {

	@Autowired
	ScheduleDao scheduleDao;
	
	@Autowired
	UserDao userDao;
	
	Schedule schedule;
	
	@Override
	public void addScheduleItem(String username, ScheduleDTO scheduleDTOs) throws ParseException {
		User user = userDao.findUserByUsername(username);
		
		schedule = new Schedule();
		
		schedule.setDoctor(new Doctor(scheduleDTOs.getDoctorId()));
		schedule.setEmployeeByEmployeeId(user.getEmployee());
		schedule.setDatetime(new SimpleDateFormat("dd-MM-yyyy").parse(scheduleDTOs.getScheduleDate()));
		schedule.setScheduleStatus(new ScheduleStatus(1));
		
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
	
	private void updateScheduleStatus(String username, List<Integer> scheduleItems, int scheduleStatus) {
		for (Iterator<Integer> iterator = scheduleItems.iterator(); iterator.hasNext();) {
			Integer scheduleItem = (Integer) iterator.next();
			
			schedule = scheduleDao.findScheduleByID(scheduleItem);
			//TODO add business roles 
			schedule.setScheduleStatus(new ScheduleStatus(scheduleStatus));
			
			try {
				scheduleDao.update(schedule);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
