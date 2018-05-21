/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.AssignServiceProviderDTO;
import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.controllers.dto.ServiceProviderInputDTO;
import com.dashda.data.entities.Contact;
import com.dashda.data.entities.District;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.EmployeeServiceProvider;
import com.dashda.data.entities.EmployeesCoveredDistrict;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.EmployeeServiceProviderDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.exception.ServiceProviderServiceExceptionManager;


/**
 * @author mhanafy
 *
 */
@Service("serviceProvidersListService")
public class ServiceProvidersListServiceImpl extends ServicesManager implements ServiceProvidersListService {

	/* (non-Javadoc)
	 * @see com.dashda.service.components.DoctorsListService#serviceList()
	 */
	
	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	EmployeeServiceProviderDao employeeServiceProviderDao;
	

	private List<District> districts;
	private ServiceProvider serviceProvider;
	private AssignServiceProviderDTO assignServiceProviderDTO;
	private List<AbstractDTO> serviceProviderDTOs;
	

	
	@Override
	public AppResponse serviceProvidersList(String username, ServiceProviderInputDTO serviceProviderInputDTO) throws ServiceProviderServiceExceptionManager {
		
		User user = userDao.findUserByUsername(username);
		districts = new ArrayList();

		
		if(user.getEmployee() == null)
			throw new ServiceProviderServiceExceptionManager(ERROR_CODE_1001);
		if(user.getEmployee().getEmployeesCoveredDistricts().isEmpty())
			throw new ServiceProviderServiceExceptionManager(ERROR_CODE_1002);
		
		for(Iterator<EmployeesCoveredDistrict> employeesCoveredDistrictIt = 
				user.getEmployee().getEmployeesCoveredDistricts().iterator(); 
				employeesCoveredDistrictIt.hasNext();) {
			districts.add((employeesCoveredDistrictIt.next()).getDistrict());
		}
		
		List<ServiceProvider> serviceProviders = 
						doctorDao.doctorsList(districts, serviceProviderInputDTO.getServiceTypeId());
		
		List<EmployeeServiceProvider> employeeServiceProviders = employeeServiceProviderDao
				.employeeServiceProviderByEmployee(user.getEmployee(), serviceProviderInputDTO.getServiceTypeId());
		
		if(serviceProviders.isEmpty())
			throw new ServiceProviderServiceExceptionManager(ERROR_CODE_1010);
		
		serviceProviderDTOs = new ArrayList();
		

		
		for(Iterator<ServiceProvider> seviceProvidersIt = serviceProviders.iterator(); seviceProvidersIt.hasNext();) {
			serviceProvider = seviceProvidersIt.next();
			
			assignServiceProviderDTO = new AssignServiceProviderDTO();
			
			assignServiceProviderDTO.setId(serviceProvider.getId());
			assignServiceProviderDTO.setDistrictName(serviceProvider.getDistrict().getEnName());
			assignServiceProviderDTO.setServiceProviderName(serviceProvider.getEnName());
			assignServiceProviderDTO.setSpeciality(serviceProvider.getSpeciality().getName());
			assignServiceProviderDTO.setGovernorateName(serviceProvider.getDistrict().getGovernorate().getEnName());
			

			for (Iterator employeeDoctorIt = employeeServiceProviders.iterator(); employeeDoctorIt.hasNext();) {
			EmployeeServiceProvider employeeServiceProvider = (EmployeeServiceProvider) employeeDoctorIt.next();
			if(employeeServiceProvider.getServiceProvider().getId() == serviceProvider.getId())
				assignServiceProviderDTO.setAssignedId(employeeServiceProvider.getId()+"");
			}
			
			
			serviceProviderDTOs.add(assignServiceProviderDTO);
		}

		
		return okListResponse(serviceProviderDTOs, "GET Service :: List Size "+ serviceProviderDTOs.size());
	}

}
