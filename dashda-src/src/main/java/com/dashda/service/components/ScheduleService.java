/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.ScheduleDTO;

/**
 * @author mhanafy
 *
 */
public interface ScheduleService {

	void addScheduleItem(String username, ScheduleDTO scheduleItems)throws ParseException;

	void approveScheduleItems(String username, List<Integer> scheduleDTOs);

	void rejectScheduleItems(String username, List<Integer> scheduleItems);

}

