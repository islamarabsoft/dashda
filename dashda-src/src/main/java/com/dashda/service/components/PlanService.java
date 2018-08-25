/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;

import javax.validation.Valid;

import com.dashda.controllers.dto.plan.CalendarActivityInputDTO;
import com.dashda.controllers.dto.plan.CreatePlanInputDTO;
import com.dashda.controllers.dto.plan.DeletePlanItemInputDTO;
import com.dashda.controllers.dto.plan.PlanInputDTO;
import com.dashda.controllers.dto.plan.PlanScheduleItemInputDTO;
import com.dashda.controllers.dto.plan.PlanScheduleItemsListInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.exception.PlanServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface PlanService {

	Object createPlan(String username, @Valid CreatePlanInputDTO createPlanInputDTO) throws ParseException, PlanServiceException;

	Object planList(String username)throws PlanServiceException, ParseException;

	Object addScheduleItems(String username, @Valid PlanScheduleItemInputDTO planScheduleItemInputDTO)throws ParseException, PlanServiceException;

	Object planItemsList(String username, @Valid PlanScheduleItemsListInputDTO planScheduleItemsListInputDTO)throws ParseException, PlanServiceException;

	void deletePlanItem(String username, @Valid DeletePlanItemInputDTO deletePlanItemInputDTO) throws PlanServiceException;

	Object submitPlanForApproval(String username, @Valid PlanInputDTO submitPlanForApprovalInputDTO)throws PlanServiceException, ParseException;

	Object approvePlan(String username, @Valid PlanInputDTO planInputDTO)throws PlanServiceException, ParseException;

	Object rejectPlan(String username, @Valid PlanInputDTO planInputDTO)throws PlanServiceException, ParseException;

	Object waitingForApprovalPlanList(String username) throws PlanServiceException, ParseException;

	Object calendarActivitiesList(String username, @Valid CalendarActivityInputDTO calenderActivityInputDTO)throws ParseException, PlanServiceException;

}
