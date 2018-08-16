/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.CreatePlanInputDTO;
import com.dashda.controllers.dto.PlanOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Plan;
import com.dashda.data.repositories.PlanDao;
import com.dashda.exception.AppExceptionHandler;
import com.dashda.exception.PlanServiceException;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class PlanServiceImpl extends ServicesManager implements PlanService {

	
	@Autowired
	PlanDao planDao;

	@Override
	public Object createPlan(String username, @Valid CreatePlanInputDTO createPlanInputDTO)
			throws ParseException, PlanServiceException {
		Employee employee;
		
		try {
			
		employee = getEmployee(username);
		}catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		Date startDate = DateUtilities.convertToDate(createPlanInputDTO.getStartDate(), DateUtilities.DATE_FORMATE_PATTERN);
		Date endDate = DateUtilities.convertToDate(createPlanInputDTO.getEndDate(), DateUtilities.DATE_FORMATE_PATTERN);
		
		Plan plan = null;
		
		plan = planDao.getPlanByDate(employee, startDate, endDate);
		if(plan != null)
			throw new PlanServiceException(ERROR_CODE_1030);
		
		plan = new Plan();
		plan.setEmployee(employee);;
		plan.setStartDate(startDate);
		plan.setEndDate(endDate);
		
		planDao.createPlan(plan);
		
		CreatePlanOutputDTO createPlanOutputDTO = new CreatePlanOutputDTO(plan.getId() 
				, DateUtilities.dateFormate(plan.getStartDate())
				, DateUtilities.dateFormate(plan.getEndDate()));
		
		return createPlanOutputDTO;
	}

	@Override
	public Object planList(String username) throws PlanServiceException, ParseException {
		Employee employee;

		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		List<PlanOutputDTO> planOutputDTOs = new ArrayList<>();
		List<Plan> plans = planDao.plansList(employee, DateUtilities.calendarYear(-1));
		for (Iterator iterator = plans.iterator(); iterator.hasNext();) {
			Plan plan = (Plan) iterator.next();
			PlanOutputDTO planOutputDTO = new PlanOutputDTO(plan.getId(), 
				DateUtilities.dateFormate(plan.getStartDate()), DateUtilities.dateFormate(plan.getEndDate()), 
				plan.getSubject(), plan.getPlanStatus().getName(), plan.getComment());
			
			planOutputDTOs.add(planOutputDTO);
		}
		
		return planOutputDTOs;
	}
	
}
