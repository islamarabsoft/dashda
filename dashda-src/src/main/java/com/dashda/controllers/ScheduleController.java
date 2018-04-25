/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.ScheduleActionDTO;
import com.dashda.controllers.dto.ScheduleDTO;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.service.components.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;

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
	public ResponseEntity<ScheduleDTO> addScheduleItem(@AuthenticationPrincipal User user, @Valid @RequestBody ScheduleDTO scheduleDTO) throws ParseException, ScheduleExceptionManager {
			return returnResponseEntityCreated(scheduleService.addScheduleItem(user.getUsername(), scheduleDTO));
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "/modify-schedule-item")
//	@Secured("ROLE_SCHEDULE_CREATOR")
//	public ResponseEntity<ScheduleDTO> modifyScheduleItem(@AuthenticationPrincipal User user, @Valid @RequestBody ScheduleDTO scheduleDTO) throws ParseException, ScheduleExceptionManager {
//			return returnResponseEntityOk(scheduleService.modifyScheduleItemData(scheduleDTO.getScheduleId(), scheduleDTO.getScheduleDate()));
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/remove-schedule-item")
//	@Secured("ROLE_SCHEDULE_CREATOR")
//	public ResponseEntity<ScheduleDTO> removeScheduleItem(@AuthenticationPrincipal User user, @Valid @RequestBody ScheduleDTO scheduleDTO) throws ParseException, ScheduleExceptionManager {
//			return returnResponseEntityAccepted(scheduleService.removeScheduleItem(scheduleDTO.getScheduleId()));
//	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/approve-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public void approveScheduleItem(@AuthenticationPrincipal User user, @RequestBody ScheduleActionDTO scheduleActionDTO) throws ScheduleExceptionManager {
				scheduleService.approveScheduleItems(user.getUsername(), scheduleActionDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reject-schedule")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public ResponseEntity rejectScheduleItem(@AuthenticationPrincipal User user, @RequestBody ScheduleActionDTO scheduleActionDTO) throws ScheduleExceptionManager {
		return returnResponseEntityOk(scheduleService.rejectScheduleItems(user.getUsername(), scheduleActionDTO));
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/schedule-items-list-need-attention")
	@Secured("ROLE_SCHEDULE_ADMIN")
	public ResponseEntity scheduleItemsList(@AuthenticationPrincipal User user) throws JsonProcessingException, ScheduleExceptionManager, ParseException {
			return returnResponseEntityOk(scheduleService.scheduleItemsListNeedAttention(user.getUsername()));
	}
}
