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

import org.hibernate.engine.jdbc.env.internal.DefaultSchemaNameResolver.SchemaNameResolverJava17Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.plan.CalendarActivityOutputDTO;
import com.dashda.controllers.dto.plan.CalendarActivityInputDTO;
import com.dashda.controllers.dto.plan.CreatePlanInputDTO;
import com.dashda.controllers.dto.plan.DeletePlanItemInputDTO;
import com.dashda.controllers.dto.plan.PlanInputDTO;
import com.dashda.controllers.dto.plan.PlanOutputDTO;
import com.dashda.controllers.dto.plan.PlanScheduleItemInputDTO;
import com.dashda.controllers.dto.plan.PlanScheduleItemOutputDTO;
import com.dashda.controllers.dto.plan.PlanScheduleItemsListInputDTO;
import com.dashda.controllers.dto.plan.PlanScheduleItemsListOutputDTO;
import com.dashda.controllers.dto.plan.WaitingForApprovalPlanOutputDTO;
import com.dashda.data.entities.CalendarActivity;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;
import com.dashda.data.entities.Plan;
import com.dashda.data.entities.PlanStatus;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ScheduleStatus;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Visit;
import com.dashda.data.entities.VisitStatus;
import com.dashda.data.repositories.VisitDao;
import com.dashda.data.repositories.employee.EmployeeHierarchyDao;
import com.dashda.data.repositories.plan.PlanDao;
import com.dashda.data.repositories.plan.ScheduleDao;
import com.dashda.data.repositories.serviceProvider.DoctorDao;
import com.dashda.enums.CalendarActivityStatusEnum;
import com.dashda.enums.PlanStatusEnum;
import com.dashda.enums.ScheduleStatusEnum;
import com.dashda.enums.VisitStatusEnum;
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
	
	@Autowired
	VisitDao visitDao;
	
	@Autowired
	EmployeeHierarchyDao employeeHierarchyDao;

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
		
		planDao.savePlan(plan);
		
		PlanOutputDTO planOutputDTO = new PlanOutputDTO(plan.getId(), 
				DateUtilities.dateFormate(plan.getStartDate()), DateUtilities.dateFormate(plan.getEndDate()), 
				plan.getSubject(), PlanStatusEnum.DRAFT.name(), PlanStatusEnum.DRAFT.getValue(), plan.getComment());
		
		return planOutputDTO;
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
				plan.getSubject(), plan.getPlanStatus().getName(), 
				plan.getPlanStatus().getId(), plan.getComment());
			
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
		
		Plan plan = planDao.findById(planScheduleItemInputDTO.getPlanId());
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

		Plan plan = planDao.findById(planScheduleItemsListInputDTO.getPlanId());
		if (plan == null) {
			throw new PlanServiceException(ERROR_CODE_1031);
		}
		List<PlanScheduleItemsListOutputDTO> scheduleItemsListOutputDTOs = new ArrayList<>();
		
		List<Schedule> schedules = scheduleDao.findListofscheduleItemsByPlan(plan);
		
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

	@Override
	public void deletePlanItem(String username, @Valid DeletePlanItemInputDTO deletePlanItemInputDTO) throws PlanServiceException {
		
		Employee employee;
		
		try {
			
		employee = getEmployee(username);
		}catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		Schedule schedule = scheduleDao.findScheduleByID(deletePlanItemInputDTO.getPlanItemId());
		if(schedule == null)
			throw new PlanServiceException(ERROR_CODE_1004);
		
		if(schedule.getEmployeeByEmployeeId().getId() != employee.getId())
			throw new PlanServiceException(ERROR_CODE_1004);
		
		scheduleDao.deleteScheduleItem(schedule);
		
	}

	@Override
	public Object submitPlanForApproval(String username,
			@Valid PlanInputDTO planInputDTO) throws PlanServiceException, ParseException {
		
		Employee employee;
		
		try {
			
		employee = getEmployee(username);
		}catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		Plan plan = planDao.findById(planInputDTO.getPlanId());
		
		if (plan == null) 
			throw new PlanServiceException(ERROR_CODE_1031);
		
		if(plan.getEmployee().getId() != employee.getId())
			throw new PlanServiceException(ERROR_CODE_1015);
		
		if(plan.getPlanStatus().getId() != PlanStatusEnum.DRAFT.getValue()
				&& plan.getPlanStatus().getId() != PlanStatusEnum.REJECTED.getValue())
			throw new PlanServiceException(ERROR_CODE_1034);
		
		List<Schedule> schedules = scheduleDao.findListofscheduleItemsByPlan(plan);
		
		if(schedules.size() == 0)
			throw new PlanServiceException(ERROR_CODE_1035);
		
		plan.setPlanStatus(new PlanStatus(
				PlanStatusEnum.SENT_FOR_APPROVAL.getValue()));
		plan.setComment(planInputDTO.getComment());
		
		planDao.savePlan(plan);
		
		PlanOutputDTO planOutputDTO = new PlanOutputDTO(plan.getId(), 
				DateUtilities.dateFormate(plan.getStartDate()), 
				DateUtilities.dateFormate(plan.getEndDate()), 
				plan.getSubject(), PlanStatusEnum.SENT_FOR_APPROVAL.name(), 
				PlanStatusEnum.SENT_FOR_APPROVAL.getValue(), plan.getComment());
		
		return planOutputDTO;
	}

	
	@Override
	public Object rejectPlan(String username, @Valid PlanInputDTO planInputDTO)
			throws PlanServiceException, ParseException {
		Employee employee;
		
		try {
			
		employee = getEmployee(username);
		}catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		Plan plan = planDao.findById(planInputDTO.getPlanId());
		
		if (plan == null) 
			throw new PlanServiceException(ERROR_CODE_1031);
		
		EmployeeHierarchy employeeHierarchy = employeeHierarchyDao.employeeHierarchy(plan.getEmployee(), employee);
		if(employeeHierarchy == null)
			throw new PlanServiceException(ERROR_CODE_1015);
		
		if(plan.getPlanStatus().getId() != 
				PlanStatusEnum.SENT_FOR_APPROVAL.getValue())
			throw new PlanServiceException(ERROR_CODE_1034);
		
		plan.setPlanStatus(new PlanStatus(
				PlanStatusEnum.REJECTED.getValue()));
		plan.setComment(planInputDTO.getComment());
		
		planDao.savePlan(plan);
		
		PlanOutputDTO planOutputDTO = new PlanOutputDTO(plan.getId(), 
				DateUtilities.dateFormate(plan.getStartDate()), DateUtilities.dateFormate(plan.getEndDate()), 
				plan.getSubject(), PlanStatusEnum.REJECTED.name(), PlanStatusEnum.REJECTED.getValue(), plan.getComment());
		
		return planOutputDTO;
	}
	
	
	@Override
	public Object approvePlan(String username, @Valid PlanInputDTO planInputDTO)
			throws PlanServiceException, ParseException {
		
		Employee employee;
		
		try {
			
		employee = getEmployee(username);
		}catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		Plan plan = planDao.findById(planInputDTO.getPlanId());
		
		if (plan == null) 
			throw new PlanServiceException(ERROR_CODE_1031);
		
		EmployeeHierarchy employeeHierarchy = employeeHierarchyDao.employeeHierarchy(plan.getEmployee(), employee);
		if(employeeHierarchy == null)
			throw new PlanServiceException(ERROR_CODE_1015);
		
		if(plan.getPlanStatus().getId() != 
				PlanStatusEnum.SENT_FOR_APPROVAL.getValue())
			throw new PlanServiceException(ERROR_CODE_1034);
		
		// get schedules items
		List<Schedule> schedules = scheduleDao.findListofscheduleItemsByPlan(plan);
		
		if(schedules.size() == 0)
			throw new PlanServiceException(ERROR_CODE_1035);
		
		for (Iterator iterator = schedules.iterator(); iterator.hasNext();) {
			Schedule schedule = (Schedule) iterator.next();
			// create visits items 
			Visit visit = new Visit();
			visit.setDatetime(schedule.getDatetime());
			visit.setEmployeeByEmployeeId(plan.getEmployee());
			visit.setServiceProvider(schedule.getServiceProvider());
			visit.setVisitStatus(new VisitStatus(VisitStatusEnum.PLANNED.getValue()));
			
			visitDao.addVisit(visit);
			
			// update schedules items by visits Id and status
			schedule.setVisit(visit);
			schedule.setScheduleStatus(new ScheduleStatus(ScheduleStatusEnum.APPROVED.getValue()));
			
			scheduleDao.update(schedule);
			
		}
		 
		
		plan.setPlanStatus(new PlanStatus(
				PlanStatusEnum.APPROVED.getValue()));
		plan.setComment(planInputDTO.getComment());
		
		planDao.updatePlan(plan);
		
		PlanOutputDTO planOutputDTO = new PlanOutputDTO(plan.getId(), 
				DateUtilities.dateFormate(plan.getStartDate()), 
				DateUtilities.dateFormate(plan.getEndDate()), 
				plan.getSubject(), PlanStatusEnum.APPROVED.name(), 
				PlanStatusEnum.APPROVED.getValue(), plan.getComment());
		
		return planOutputDTO;
	}

	@Override
	public Object waitingForApprovalPlanList(String username) throws PlanServiceException, ParseException {
		Employee employee;

		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new PlanServiceException(e.getErrorCode());
		}
		
		List<WaitingForApprovalPlanOutputDTO> planOutputDTOs = new ArrayList<>();
		List<Plan> plans = planDao.waitingForApprovalPlansList(employee);
		for (Iterator iterator = plans.iterator(); iterator.hasNext();) {
			Plan plan = (Plan) iterator.next();
			WaitingForApprovalPlanOutputDTO planOutputDTO = new WaitingForApprovalPlanOutputDTO(plan.getId(), 
				plan.getEmployee().getName(), DateUtilities.dateFormate(plan.getStartDate()), DateUtilities.dateFormate(plan.getEndDate()), 
				plan.getSubject(), plan.getPlanStatus().getName(), 
				plan.getPlanStatus().getId(), plan.getComment());
			
			planOutputDTOs.add(planOutputDTO);
		}
		
		return planOutputDTOs;
	}

	@Override
	public Object calendarActivitiesList(String username, @Valid CalendarActivityInputDTO calendarActivityInputDTO)
			throws ParseException, PlanServiceException {
		List<CalendarActivityOutputDTO> activityOutputDTOs = new ArrayList<>();
		
		int employeeId = calendarActivityInputDTO.getEmployeeId();
		Date dateFrom = DateUtilities.convertToDate(calendarActivityInputDTO.getDateFrom(), 
				DateUtilities.DATE_FORMATE_PATTERN);
		Date dateTo = DateUtilities.convertToDate(calendarActivityInputDTO.getDateTo(), 
				DateUtilities.DATE_FORMATE_PATTERN);
		
		List<CalendarActivity> calendarActivities = planDao.getCalendarActiviiesList(employeeId, dateFrom, dateTo);
		
		for (Iterator iterator = calendarActivities.iterator(); iterator.hasNext();) {
			CalendarActivity calendarActivity = (CalendarActivity) iterator.next();
			
			CalendarActivityOutputDTO calendarActivitiesOutputDTO = 
					new CalendarActivityOutputDTO();
					
					mapper.map(calendarActivity, calendarActivitiesOutputDTO);
					
					if(calendarActivity.getVisitStatusId() != null) {
						switch (calendarActivity.getVisitStatusId()) {
						case 1:
							calendarActivitiesOutputDTO.setStatus(CalendarActivityStatusEnum.PLANNED.name());
							calendarActivitiesOutputDTO.setStatusId(CalendarActivityStatusEnum.PLANNED.getValue());
							break;
						case 2:
							calendarActivitiesOutputDTO.setStatus(CalendarActivityStatusEnum.VISITED.name());
							calendarActivitiesOutputDTO.setStatusId(CalendarActivityStatusEnum.VISITED.getValue());
							break;
						case 3:
							calendarActivitiesOutputDTO.setStatus(CalendarActivityStatusEnum.DISCARD.name());
							calendarActivitiesOutputDTO.setStatusId(CalendarActivityStatusEnum.DISCARD.getValue());
							break;
						default:
							break;
						}
					}else if(calendarActivity.getSpecialty() != null){
						calendarActivitiesOutputDTO.setStatus(CalendarActivityStatusEnum.DRAFT.name());
						calendarActivitiesOutputDTO.setStatusId(CalendarActivityStatusEnum.DRAFT.getValue());
					}else {
						calendarActivitiesOutputDTO.setStatus(CalendarActivityStatusEnum.OFF_VISIT.name());
						calendarActivitiesOutputDTO.setStatusId(CalendarActivityStatusEnum.OFF_VISIT.getValue());
					}
					
					activityOutputDTOs.add(calendarActivitiesOutputDTO);
			
		}

		return activityOutputDTOs;
	}

	
}
