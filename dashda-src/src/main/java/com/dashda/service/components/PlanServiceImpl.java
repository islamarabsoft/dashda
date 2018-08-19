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
import com.dashda.controllers.dto.PlanScheduleItemInputDTO;
import com.dashda.controllers.dto.PlanScheduleItemOutputDTO;
import com.dashda.controllers.dto.PlanScheduleItemsListInputDTO;
import com.dashda.controllers.dto.PlanScheduleItemsListOutputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Plan;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.PlanDao;
import com.dashda.data.repositories.ScheduleDao;
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
	
	@Autowired
	ScheduleDao scheduleDao; 
	
	@Autowired
	DoctorDao serviceProviderDao;

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

	@Override
	public Object addScheduleItems(String username, @Valid PlanScheduleItemInputDTO planScheduleItemInputDTO)
			throws ParseException, PlanServiceException {
		
		Employee employee;
		
		try {
			
		employee = getEmployee(username);
		}catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		List<PlanScheduleItemOutputDTO> planScheduleItemOutputDTOs = new ArrayList<>();
		int [] serviceProviderIds = planScheduleItemInputDTO.getServiceProviderIds();
		Date scheduleDate = DateUtilities.convertToDate(planScheduleItemInputDTO.getDate(), DateUtilities.DATE_FORMATE_PATTERN);
		
		Plan plan = planDao.getById(planScheduleItemInputDTO.getPlanId());
		if (plan == null) {
			throw new PlanServiceException(ERROR_CODE_1031);
		}
		if(DateUtilities.compareTwoDates(scheduleDate, plan.getStartDate()) == 1)
			throw new PlanServiceException(ERROR_CODE_1032);
		
		if(DateUtilities.compareTwoDates(scheduleDate, plan.getEndDate()) == -1)
			throw new PlanServiceException(ERROR_CODE_1032);
		
		if(DateUtilities.compareTwoDates(new Date(), scheduleDate) == -1)
			throw new PlanServiceException(ERROR_CODE_1013);
		
		
		for (int i = 0; i < serviceProviderIds.length; i++) {
			Schedule schedule = new Schedule();
			ServiceProvider serviceProvider = serviceProviderDao.findDoctorById(serviceProviderIds[i]);
			
			if (serviceProvider == null) {
				throw new PlanServiceException(ERROR_CODE_1011);
			}
			schedule.setDatetime(scheduleDate);
			schedule.setPlan(plan);
			schedule.setEmployeeByEmployeeId(employee);
			schedule.setEmployeeByManagerId(employee.getManager());
			schedule.setScheduleStatus(new ScheduleStatus(1));
			schedule.setServiceProvider(serviceProvider);
			
			scheduleDao.saveScheduleItem(schedule);
			if (schedule.getId() == 0) {
				throw new PlanServiceException(ERROR_CODE_1033);
			}
			PlanScheduleItemOutputDTO planScheduleItemOutputDTO = new PlanScheduleItemOutputDTO(schedule.getId(), 
					serviceProvider.getId(), employee.getId(), DateUtilities.dateFormate(scheduleDate));
			
			planScheduleItemOutputDTOs.add(planScheduleItemOutputDTO);
		}
		
		return planScheduleItemOutputDTOs;
	}

	@Override
	public List<PlanScheduleItemsListOutputDTO> planItemsList(String username, @Valid PlanScheduleItemsListInputDTO planScheduleItemsListInputDTO)
			throws ParseException, PlanServiceException {
		
		Employee employee;
		
		try {
			
		employee = getEmployee(username);
		}catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		Plan plan = planDao.getById(planScheduleItemsListInputDTO.getPlanId());
		if (plan == null) {
			throw new PlanServiceException(ERROR_CODE_1031);
		}
		List<PlanScheduleItemsListOutputDTO> scheduleItemsListOutputDTOs = new ArrayList<>();
		
		List<Schedule> schedules = scheduleDao.findListofscheduleItemsByPlan(employee, plan);
		
		for (Iterator iterator = schedules.iterator(); iterator.hasNext();) {
			Schedule schedule = (Schedule) iterator.next();
			
			ServiceProvider serviceProvider = schedule.getServiceProvider();
			PlanScheduleItemsListOutputDTO scheduleItemsListOutputDTO = new PlanScheduleItemsListOutputDTO(schedule.getId(),
					serviceProvider.getId(), getServiceProviderName(serviceProvider), serviceProvider.getSpecialty().getName(),
					serviceProvider.getDistrict().getEnName(),DateUtilities.dateFormate(schedule.getDatetime()),
					schedule.getScheduleStatus().getName());
					
			
			scheduleItemsListOutputDTOs.add(scheduleItemsListOutputDTO);
		}
		
		return scheduleItemsListOutputDTOs;
	}
	
}
