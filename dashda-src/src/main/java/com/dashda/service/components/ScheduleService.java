/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import com.dashda.controllers.dto.AddScheduleInputDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.ModifyScheduleInputDTO;
import com.dashda.controllers.dto.ScheduleActionDTO;
import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.controllers.dto.ScheduleListInputDTO;
import com.dashda.exception.ScheduleExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface ScheduleService {

	public AppResponse addScheduleItem(String username, AddScheduleInputDTO addScheduleInputDTO)throws ParseException, ScheduleExceptionManager;

	public AppResponse approveScheduleItems(String username, ScheduleActionDTO scheduleActionDTO)throws ScheduleExceptionManager;

	public AppResponse discardScheduleItems(String username, ScheduleActionDTO scheduleActionDTO)throws ScheduleExceptionManager;
	
	public AppResponse scheduleItemsListNeedAttention(String username, ScheduleListInputDTO scheduleListInputDTO) throws ScheduleExceptionManager, ParseException;

	public AppResponse modifyScheduleItemData(ModifyScheduleInputDTO modifyScheduleInputDTO) throws ParseException, ScheduleExceptionManager;

	public AppResponse removeScheduleItem(int scheduleId) throws ScheduleExceptionManager;


	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	AppResponse approveSchedule(String username, int subordinateId);
//	
//	AppResponse rejectSchedule(String username, int subordinateId);
}

