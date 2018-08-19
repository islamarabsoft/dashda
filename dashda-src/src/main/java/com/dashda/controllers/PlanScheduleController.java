/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.annotation.RestResponseEntity;
import com.dashda.controllers.dto.PlanScheduleItemInputDTO;
import com.dashda.controllers.dto.PlanScheduleItemsListInputDTO;
import com.dashda.enums.ReturnType;
import com.dashda.exception.PlanServiceException;
import com.dashda.service.components.PlanService;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class PlanScheduleController {

	@Autowired
	PlanService planService;
	
	@RestResponseEntity(returnType=ReturnType.LIST)
	@RequestMapping(method = RequestMethod.POST, value="/add-plan-items")
	@Secured("ROLE_SCHEDULE_CREATOR")
	public Object addPlanItems(@AuthenticationPrincipal User user
			,@RequestBody @Valid PlanScheduleItemInputDTO planScheduleItemInputDTO) 
					throws PlanServiceException, ParseException {
		
		return planService.addScheduleItems(user.getUsername(), planScheduleItemInputDTO);
	}
	
	@RestResponseEntity(returnType=ReturnType.LIST)
	@RequestMapping(method = RequestMethod.POST, value="/plan-items-list")
	@Secured("ROLE_SCHEDULE_CREATOR")
	public Object planItemsList(@AuthenticationPrincipal User user
			,@RequestBody @Valid PlanScheduleItemsListInputDTO planScheduleItemsListInputDTO) 
					throws PlanServiceException, ParseException {
		
		return planService.planItemsList(user.getUsername(), planScheduleItemsListInputDTO);
	}
}
