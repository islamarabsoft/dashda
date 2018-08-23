/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;

import javax.validation.Valid;

import com.dashda.controllers.dto.CreatePlanInputDTO;
import com.dashda.controllers.dto.DeletePlanItemInputDTO;
import com.dashda.controllers.dto.PlanScheduleItemInputDTO;
import com.dashda.controllers.dto.PlanScheduleItemsListInputDTO;
import com.dashda.controllers.dto.SubmitPlanForApprovalInputDTO;
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

	Object submitPlanForApproval(String username, @Valid SubmitPlanForApprovalInputDTO submitPlanForApprovalInputDTO)throws PlanServiceException, ParseException;

}
