/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.SpecialtyOutputDto;
import com.dashda.controllers.dto.employee.EmployeeOutputDTO;
import com.dashda.data.entities.District;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Specialty;
import com.dashda.data.repositories.report.ReportLookupDao;
import com.dashda.exception.AppExceptionHandler;

/**
 * @author mohamed.hanfy
 *
 */
@Service
public class ReportLookupServiceImpl extends ServicesManager implements ReportLookupService {

	@Autowired
	ReportLookupDao reportLookupDao;
	
	@Override
	public AppResponse findSubordinates(String username) throws ReportLookupServiceException {

		Employee employee = null;
		
		
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportLookupServiceException(e.getErrorCode());
		}
		
		List employees = new ArrayList();
		
		List<Employee> employeeLists = reportLookupDao.findSubordinates(employee);
		for (Iterator employeeHierarchyIt = employeeLists.iterator(); employeeHierarchyIt.hasNext();) {
			Employee employee2 = (Employee) employeeHierarchyIt.next();
			EmployeeOutputDTO employeeOutputDTO = new EmployeeOutputDTO();
			
			employeeOutputDTO.setId(employee2.getId());
			employeeOutputDTO.setName(employee2.getName());
			employeeOutputDTO.setManagerId(employee2.getManager().getId());
			employees.add(employeeOutputDTO);
		}
		
 		return okListResponse(employees);
	}

	@Override
	public AppResponse findSubordinatesWithoutLowLevel(String username) throws ReportLookupServiceException {
		Employee employee = null;
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportLookupServiceException(e.getErrorCode());
		}
		
		List employees = new ArrayList();
		
		List<Employee> employeeQueryList = reportLookupDao.findSubordinatesWithoutLowLevel(employee);
		
		for (Iterator employeeHierarchyIt = employeeQueryList.iterator(); employeeHierarchyIt.hasNext();) {
			Employee employee2 = (Employee) employeeHierarchyIt.next();
			EmployeeOutputDTO employeeOutputDTO = new EmployeeOutputDTO();
			
			employeeOutputDTO.setId(employee2.getId());
			employeeOutputDTO.setName(employee2.getName());
			employeeOutputDTO.setManagerId(employee2.getManager().getId());
			employees.add(employeeOutputDTO);
		}
		
 		return okListResponse(employees);
	}

	@Override
	public AppResponse findServiceProviderDistrict(String username) throws ReportLookupServiceException {
		Employee employee = null;
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportLookupServiceException(e.getErrorCode());
		}
		
		List districtOutputDTOs = new ArrayList();
		List<District> districtOutputs = reportLookupDao.findAssignedDistrict(employee.getId());
		for (Iterator iterator = districtOutputs.iterator(); iterator.hasNext();) {
			District district = (District) iterator.next();
			DistrictOutputDTO districtOutputDTO = new DistrictOutputDTO(district.getId(), district.getEnName());
			
			districtOutputDTOs.add(districtOutputDTO);
		}

		return okListResponse(districtOutputDTOs);
	}

	@Override
	public AppResponse findServiceProviderSpecialty(String username) throws ReportLookupServiceException {
		Employee employee = null;
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportLookupServiceException(e.getErrorCode());
		}
		
		List specialtyOutputDTOs= new ArrayList();
		
		List<Specialty> districtOutputs = reportLookupDao.findAssignedSpecialty(employee.getId());
		for (Iterator specialtyIt = districtOutputs.iterator(); specialtyIt.hasNext();) {
			Specialty specialty = (Specialty) specialtyIt.next();
			SpecialtyOutputDto specialtyOutputDto = new SpecialtyOutputDto(specialty.getId(), specialty.getName());
			
			specialtyOutputDTOs.add(specialtyOutputDto);
		}

		return okListResponse(specialtyOutputDTOs);
	}

	@Override
	public AppResponse findServiceProvider(String username) throws ReportLookupServiceException {
		Employee employee = null;
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportLookupServiceException(e.getErrorCode());
		}
		
		List serviceProviderOutputDTOs= new ArrayList();
		List<ServiceProvider> serviceProviderOutputs = reportLookupDao.findAssignedServiceProvider(employee.getId());
		for (Iterator serviceProviderIt = serviceProviderOutputs.iterator(); serviceProviderIt.hasNext();) {
			
			ServiceProvider serviceProvider = (ServiceProvider) serviceProviderIt.next();
			ServiceProviderLookupDTO specialtyOutputDto = new ServiceProviderLookupDTO(serviceProvider.getId()
					, serviceProvider.getFirstName() +" "+serviceProvider.getLastName()
					, serviceProvider.getSpecialty().getId(), serviceProvider.getDistrict().getId());
			
			serviceProviderOutputDTOs.add(specialtyOutputDto);
		}

		return okListResponse(serviceProviderOutputDTOs);
	}

	@Override
	public AppResponse findSubordinatesLowLevel(String username) throws ReportLookupServiceException {
		Employee employee = null;
		try {
			employee = getEmployee(username);
		} catch (AppExceptionHandler e) {
			throw new ReportLookupServiceException(e.getErrorCode());
		}
		
		List employees = new ArrayList();
		
		List<Employee> employeeQueryList = reportLookupDao.findSubordinatesLowLevel(employee);
		
		for (Iterator employeeHierarchyIt = employeeQueryList.iterator(); employeeHierarchyIt.hasNext();) {
			Employee employee2 = (Employee) employeeHierarchyIt.next();
			EmployeeOutputDTO employeeOutputDTO = new EmployeeOutputDTO();
			
			employeeOutputDTO.setId(employee2.getId());
			employeeOutputDTO.setName(employee2.getName());
			employeeOutputDTO.setManagerId(employee2.getManager().getId());
			employees.add(employeeOutputDTO);
		}
		
 		return okListResponse(employees);
	}

}
