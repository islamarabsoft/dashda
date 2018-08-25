/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashda.controllers.dto.employee.EmployeeDoctorDTO;
import com.dashda.controllers.dto.serviceProvider.DoctorDTO;
import com.dashda.controllers.dto.serviceProvider.MyServiceProviderDTO;
import com.dashda.controllers.dto.serviceProvider.MyServiceProviderInputDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeServiceProvider;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.entities.VisitStatus;
import com.dashda.data.repositories.VisitDao;
import com.dashda.data.repositories.employee.EmployeeServiceProviderDao;
import com.dashda.data.repositories.plan.ScheduleDao;
import com.dashda.data.repositories.serviceProvider.DoctorDao;
import com.dashda.data.repositories.user.UserDao;
import com.dashda.enums.ScheduleStatusEnum;
import com.dashda.enums.VisitStatusEnum;
import com.dashda.exception.MyServiceProvidersListServiceExceptionManager;
import com.dashda.utilities.DateUtilities;

/**
 * @author mhanafy
 *
 */
@Component
public class MyServiceProvidersListServiceImpl extends ServicesManager implements MyServiceProvidersListService {

	@Autowired
	EmployeeServiceProviderDao employeeServiceProviderDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	VisitDao visitDao;
	
	@Autowired
	ScheduleDao scheduleDao;
	
	private MyServiceProviderDTO myServiceProviderDTO;
	private List myServiceProviderDTOs;
	private List<EmployeeServiceProvider> employeeServiceProviders;
	private User user;
	private EmployeeServiceProvider employeeServiceProvider;
	private ServiceProvider serviceProvider;
	private Employee employee;

	
	/* (non-Javadoc)
	 * @see com.dashda.controllers.MyDoctorsListService#myDoctorsList(java.lang.String)
	 */
	@Override
	public AppResponse myServiceProvidersList(String username, MyServiceProviderInputDTO myServiceProviderInputDTO) throws MyServiceProvidersListServiceExceptionManager, ParseException {
		user = userDao.findUserByUsername(username);
		
		
		if(user.getEmployee() == null)
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1001);
	
		employeeServiceProviders = employeeServiceProviderDao.employeeServiceProviderByEmployee(
				user.getEmployee(), myServiceProviderInputDTO.getServiceTypeId());
		
		myServiceProviderDTOs = new ArrayList();
		
		for(Iterator<EmployeeServiceProvider> serviceProvidersIt = employeeServiceProviders.iterator(); serviceProvidersIt.hasNext();) {
			myServiceProviderDTO = new MyServiceProviderDTO();
			
			employeeServiceProvider = serviceProvidersIt.next();
			ServiceProvider serviceProvider = employeeServiceProvider.getServiceProvider();
			
			myServiceProviderDTO.setDistrictName(serviceProvider.getDistrict().getEnName());
			myServiceProviderDTO.setId(serviceProvider.getId());
			myServiceProviderDTO.setServiceProviderName(serviceProvider.getFirstName() 
					+ " " + serviceProvider.getLastName());
			myServiceProviderDTO.setSpecialty(serviceProvider.getSpecialty().getName());
			myServiceProviderDTO.setSpecialtyId(serviceProvider.getSpecialty().getId());
			myServiceProviderDTO.setServiceProviderTypeId(serviceProvider.getServiceProviderType().getId()+"");
			myServiceProviderDTO.setGovernorateName(serviceProvider.getDistrict().getGovernorate().getEnName());
			
			for (Iterator scheduleIt = serviceProvider.getSchedules().iterator(); scheduleIt.hasNext();) {
				Schedule schedule = (Schedule) scheduleIt.next();
				if(schedule.getScheduleStatus().getId() == ScheduleStatusEnum.PENDING_APPROVAL.getValue()) {

					myServiceProviderDTO.setScheduleId(schedule.getId()+"");
					myServiceProviderDTO.setScheduleDate(DateUtilities.dateFormate(schedule.getDatetime()));
				}
			}
			
			myServiceProviderDTO.setAssignedId(employeeServiceProvider.getId()+"");
			
			//Get Last visit
			Visit visit = visitDao.findCompletedVisitByDoctorAndEmployee(serviceProvider, employeeServiceProvider.getEmployee());
			if(visit != null) {
				
				String lastVisitStatus = null;
				String lastVisitDate = null;
				if(visit.getVisitStatus().getId() == VisitStatusEnum.PLANNED.getValue()) {
					lastVisitStatus = "0";
					lastVisitDate = DateUtilities.dateFormate(visit.getDatetime());
				}else if(visit.getVisitStatus().getId() == VisitStatusEnum.COMPLETE.getValue()){
					lastVisitStatus = "1";
					lastVisitDate = DateUtilities.dateFormate(visit.getDatetime());
				}
				myServiceProviderDTO.setLastVisitStatus(lastVisitStatus);
				myServiceProviderDTO.setLastVisitDate(lastVisitDate);
			}
			
			myServiceProviderDTOs.add(myServiceProviderDTO);
		}

		return okListResponse(myServiceProviderDTOs, "GET Service :: List Size "+ myServiceProviderDTOs.size());
	}

	@Override
	public void saveMyServiceProvidersList(String username, List<Integer> serviceProviders, int serviceProviderTypeId) throws MyServiceProvidersListServiceExceptionManager {
		
		user = userDao.findUserByUsername(username);
		
		employee = user.getEmployee();
		
		if(employee == null)
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1001);
		
		employeeServiceProviders = new ArrayList();
		
		List<EmployeeServiceProvider> oldEmployeeServiceProviders = employeeServiceProviderDao.employeeServiceProviderByEmployee(employee, serviceProviderTypeId);
		
		//clear old list
		employeeServiceProviderDao.clearMyServiceProvidersList(oldEmployeeServiceProviders);
		
		
		//Generate new list of EmployeeDoctors
		for (Iterator<Integer> iterator = serviceProviders.iterator(); iterator.hasNext();) {
			Integer serviceProviderId = iterator.next();
			
			serviceProvider = doctorDao.findDoctorById(serviceProviderId);
			
			if(serviceProvider == null)
				throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1011 + " '" + serviceProviderId + "'");
			
			employeeServiceProvider = new EmployeeServiceProvider(employee, serviceProvider);
			
			employeeServiceProviders.add(employeeServiceProvider);
		}
		
		employeeServiceProviderDao.createMyServiceProvidersList(employeeServiceProviders);
	}

	@Override
	public AppResponse assignServiceProviderToMyList(String username, Integer serviceProviderId)
			throws MyServiceProvidersListServiceExceptionManager {
		user = userDao.findUserByUsername(username);
		
		employee = user.getEmployee();
		if(employee == null)
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1001);
		int employeeId = employee.getId();
		
		serviceProvider = doctorDao.findDoctorById(serviceProviderId);
		if(serviceProvider == null)
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1011);
		if(employeeServiceProviderDao.findEmployeeServiceProviderByEmployeeIdAndServiceProviderId(employeeId, serviceProviderId) !=null)
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1017);
		
		employeeServiceProvider = new EmployeeServiceProvider();
		employeeServiceProvider.setServiceProvider(serviceProvider);
		employeeServiceProvider.setEmployee(employee);
		
		employeeServiceProviderDao.addServiceProviderToMyList(employeeServiceProvider);
		
		EmployeeDoctorDTO employeeDoctorDTO = new EmployeeDoctorDTO();
		
		employeeDoctorDTO.setAssignedId(employeeServiceProvider.getId());
		employeeDoctorDTO.setServiceProviderId(serviceProviderId);
		employeeDoctorDTO.setEmployeeId(employeeId);
		
		
		return createResponse(employeeDoctorDTO, "Service Provider Assigned to Employee List Successfully");
	}

	@Override
	public AppResponse unassignServiceProviderToMyList(String username, int assignedId)
			throws MyServiceProvidersListServiceExceptionManager {
		if(assignedId == 0)	
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1020);
		
		EmployeeServiceProvider employeeServiceProvider = employeeServiceProviderDao
					.findEmployeeServiceProviderById(assignedId);
		if(employeeServiceProvider == null)
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1020);
		
		List<Schedule> schedules = scheduleDao.findScheduleItemNotApproved(employeeServiceProvider.getServiceProvider()
													, employeeServiceProvider.getEmployee());
		if(!schedules.isEmpty())
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1021);
		
		List<Visit> visits = visitDao.findVisitNotComplete(employeeServiceProvider.getServiceProvider()
				, employeeServiceProvider.getEmployee());
		if(!visits.isEmpty())
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1022);
		
		employeeServiceProviderDao.removeEmployeeServiceProvider(employeeServiceProvider);
		
		return emptyResponse("Object Deleted Successfully {"+assignedId+"}");
	}

	@Override
	public AppResponse serviceProviderNameList(String username) throws MyServiceProvidersListServiceExceptionManager {
		
		
		user = userDao.findUserByUsername(username);
		
		
		if(user.getEmployee() == null)
			throw new MyServiceProvidersListServiceExceptionManager(ERROR_CODE_1001);
	
		employeeServiceProviders = employeeServiceProviderDao.employeeServiceProviderByEmployee(
				user.getEmployee());
		
		myServiceProviderDTOs = new ArrayList();
		
		for(Iterator<EmployeeServiceProvider> serviceProvidersIt = employeeServiceProviders.iterator(); serviceProvidersIt.hasNext();) {
			EmployeeServiceProvider provider = (EmployeeServiceProvider)serviceProvidersIt.next();
			ServiceProviderLookupDTO serviceProviderNameDTO = new ServiceProviderLookupDTO(provider.getServiceProvider().getId()
					, provider.getServiceProvider().getFirstName() +" "+provider.getServiceProvider().getLastName());
			
			myServiceProviderDTOs.add(serviceProviderNameDTO);
		}
		
		return okListResponse(myServiceProviderDTOs);
	}

}
