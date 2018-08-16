/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;

import javax.validation.Valid;

import com.dashda.controllers.dto.CreatePlanInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.exception.PlanServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface PlanService {

	Object createPlan(String username, @Valid CreatePlanInputDTO createPlanInputDTO) throws ParseException, PlanServiceException;

	Object planList(String username)throws PlanServiceException, ParseException;

}
