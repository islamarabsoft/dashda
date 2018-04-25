/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.ScheduleActionDTO;
import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.exception.ScheduleExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface ScheduleService {

	public AppResponse addScheduleItem(String username, ScheduleDTO scheduleItems)throws ParseException, ScheduleExceptionManager;

	AppResponse approveScheduleItems(String username, ScheduleActionDTO scheduleActionDTO)throws ScheduleExceptionManager;

	AppResponse rejectScheduleItems(String username, ScheduleActionDTO scheduleActionDTO)throws ScheduleExceptionManager;

//	AppResponse approveSchedule(String username, int subordinateId);
//	
//	AppResponse rejectSchedule(String username, int subordinateId);

	public AppResponse scheduleItemsListNeedAttention(String username) throws ScheduleExceptionManager, ParseException;

	public AppResponse modifyScheduleItemData(int scheduleId, String scheduleDate) throws ParseException, ScheduleExceptionManager;

	public AppResponse removeScheduleItem(int scheduleId) throws ScheduleExceptionManager;


}

