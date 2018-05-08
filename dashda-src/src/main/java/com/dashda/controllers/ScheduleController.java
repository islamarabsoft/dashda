/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.AddScheduleInputDTO;
import com.dashda.controllers.dto.ModifyScheduleInputDTO;
import com.dashda.controllers.dto.RemoveScheduleInputDTO;
import com.dashda.controllers.dto.ScheduleActionDTO;
import com.dashda.controllers.dto.ScheduleListInputDTO;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.service.components.ScheduleService;

/**
 * @author mhanafy
 *
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends AbstractController{

	
	@Autowired
	ScheduleService scheduleService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/add-schedule-item")
	@Secured("ROLE_SCHEDULE_CREATOR")
	public ResponseEntity addScheduleItem(@AuthenticationPrincipal User user, 
			@Valid @RequestBody AddScheduleInputDTO addScheduleInputDTO) throws ParseException, ScheduleExceptionManager {
		
			return returnResponseEntityCreated(scheduleService.addScheduleItem(user.getUsername(), addScheduleInputDTO));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/modify-schedule-item")
	@Secured("ROLE_SCHEDULE_CREATOR")
	public ResponseEntity modifyScheduleItem(@AuthenticationPrincipal User user, 
			@Valid @RequestBody ModifyScheduleInputDTO modifyScheduleInputDTO) 
									throws ParseException, ScheduleExceptionManager {
		
			return returnResponseEntityOk(scheduleService.modifyScheduleItemData(modifyScheduleInputDTO));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/remove-schedule-item")
	@Secured("ROLE_SCHEDULE_CREATOR")
	public ResponseEntity removeScheduleItem(@AuthenticationPrincipal User user, 
			@Valid @RequestBody RemoveScheduleInputDTO removeScheduleInputDTO) throws ScheduleExceptionManager {
		
			return returnResponseEntityAccepted(scheduleService.removeScheduleItem(removeScheduleInputDTO.getScheduleId()));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/approve-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public ResponseEntity approveScheduleItem(@AuthenticationPrincipal User user, 
			@Valid @RequestBody ScheduleActionDTO scheduleActionDTO) throws ScheduleExceptionManager {
		
		return returnResponseEntityCreated(scheduleService.approveScheduleItems(user.getUsername(), scheduleActionDTO));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reject-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public ResponseEntity rejectScheduleItem(@AuthenticationPrincipal User user, 
			@Valid @RequestBody ScheduleActionDTO scheduleActionDTO) throws ScheduleExceptionManager {
		
		return returnResponseEntityOk(scheduleService.discardScheduleItems(user.getUsername(), scheduleActionDTO));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/schedule-items-list-need-attention")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public ResponseEntity scheduleItemsList(@AuthenticationPrincipal User user, 
			@Valid @RequestBody ScheduleListInputDTO scheduleListInputDTO) throws ScheduleExceptionManager, ParseException {
		
			return returnResponseEntityOk(scheduleService.
						scheduleItemsListNeedAttention(user.getUsername(), scheduleListInputDTO));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	@RequestMapping(method = RequestMethod.POST, value = "/approve-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public void approveSchedule(@AuthenticationPrincipal User user, @RequestBody int employeeId) {
			scheduleService.approveSchedule(user.getUsername(), employeeId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reject-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public void rejectSchedule(@AuthenticationPrincipal User user, @RequestBody int employeeId) {
			scheduleService.rejectSchedule(user.getUsername(), employeeId);
	}**/
	
}
