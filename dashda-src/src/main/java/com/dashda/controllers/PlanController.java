/**
 * 
 */
package com.dashda.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.annotation.RestResponseEntity;
import com.dashda.controllers.dto.plan.CalendarActivityInputDTO;
import com.dashda.controllers.dto.plan.CreatePlanInputDTO;
import com.dashda.controllers.dto.plan.PlanInputDTO;
import com.dashda.enums.ReturnType;
import com.dashda.exception.PlanServiceException;
import com.dashda.service.components.PlanService;

/**
 * @author mohamed.hanfy
 *
 */
@RestController
public class PlanController extends AbstractController {

	@Autowired
	PlanService planService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/create-plan")
	@Secured("ROLE_SCHEDULE_CREATOR")
	@RestResponseEntity(status = HttpStatus.OK, returnType = ReturnType.CREATE)
	public Object createPlan(@AuthenticationPrincipal User user, 
			@Valid @RequestBody CreatePlanInputDTO createPlanInputDTO) throws ParseException, PlanServiceException{
		
			return planService.createPlan(user.getUsername(), createPlanInputDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/plans-list")
	@Secured("ROLE_SCHEDULE_CREATOR")
	@RestResponseEntity(returnType = ReturnType.LIST)
	public Object planList(@AuthenticationPrincipal User user) 
			throws ParseException, PlanServiceException{
		
			return planService.planList(user.getUsername());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/submit-plan-for-approval")
	@Secured("ROLE_SCHEDULE_CREATOR")
	@RestResponseEntity(returnType = ReturnType.UPDATE)
	public Object submitPlanForApproval(@AuthenticationPrincipal User user, 
			@Valid @RequestBody PlanInputDTO planInputDTO) 
			throws ParseException, PlanServiceException{
		
			return planService.submitPlanForApproval(user.getUsername(), 
					planInputDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/approve-plan")
	@Secured("ROLE_SCHEDULE_ADMIN")
	@RestResponseEntity(returnType = ReturnType.UPDATE)
	public Object approvePlan(@AuthenticationPrincipal User user, 
			@Valid @RequestBody PlanInputDTO planInputDTO) 
			throws ParseException, PlanServiceException{
		
			return planService.approvePlan(user.getUsername(), 
					planInputDTO);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/reject-plan")
	@Secured("ROLE_SCHEDULE_ADMIN")
	@RestResponseEntity(returnType = ReturnType.UPDATE)
	public Object rejectPlan(@AuthenticationPrincipal User user, 
			@Valid @RequestBody PlanInputDTO planInputDTO) 
			throws ParseException, PlanServiceException{
		
			return planService.rejectPlan(user.getUsername(), 
					planInputDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/waiting-for-approval-plans-list")
	@Secured("ROLE_SCHEDULE_ADMIN")
	@RestResponseEntity(returnType = ReturnType.LIST)
	public Object waitingForApprovalPlanList(@AuthenticationPrincipal User user) 
			throws ParseException, PlanServiceException{
		
			return planService.waitingForApprovalPlanList(user.getUsername());
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/calender-activities")
	@Secured("ROLE_SCHEDULE_CREATOR")
	@RestResponseEntity(status = HttpStatus.OK, returnType = ReturnType.LIST)
	public Object calendarActivitiesList(@AuthenticationPrincipal User user, 
			@Valid @RequestBody CalendarActivityInputDTO calenderActivityInputDTO)
					throws ParseException, PlanServiceException{
		
			return planService.calendarActivitiesList(user.getUsername(), 
					calenderActivityInputDTO);
	}
}
