/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.exception.ScheduleExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface ScheduleService {

	void addScheduleItem(String username, ScheduleDTO scheduleItems)throws ParseException, ScheduleExceptionManager;

	void approveScheduleItems(String username, List<Integer> scheduleDTOs)throws ScheduleExceptionManager;

	void rejectScheduleItems(String username, List<Integer> scheduleItems)throws ScheduleExceptionManager;

	void approveSchedule(String username, int subordinateId);
	
	void rejectSchedule(String username, int subordinateId);

	List<ScheduleDTO> scheduleItemsListNeedAttention(String username) throws ScheduleExceptionManager;


}

