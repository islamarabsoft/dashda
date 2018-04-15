/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.data.entities.Doctor;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeDoctor;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.EmployeeDoctorDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.exception.MyDoctorsListServiceExceptionManager;

/**
 * @author mhanafy
 *
 */
@Component
public class MyDoctorsListServiceImpl extends ServicesManager implements MyDoctorsListService {

	@Autowired
	EmployeeDoctorDao employeeDoctorDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DoctorDao doctorDao;
	
	private DoctorDTO doctorDTO;
	
	private List<DoctorDTO> doctorDTOs;
	
	private List<EmployeeDoctor> employeeDoctors;
	
	private User user;
	
	private EmployeeDoctor employeeDoctor;
	
	private Doctor doctor;
	
	private Employee employee;
	
	/* (non-Javadoc)
	 * @see com.dashda.controllers.MyDoctorsListService#myDoctorsList(java.lang.String)
	 */
	@Override
	public List<DoctorDTO> myDoctorsList(String username) throws MyDoctorsListServiceExceptionManager {
		
		user = userDao.findUserByUsername(username);
		
		if(user.getEmployee() == null)
			throw new MyDoctorsListServiceExceptionManager(ERROR_CODE_1001);
		
		employeeDoctors = employeeDoctorDao.employeeDoctorsByEmployee(user.getEmployee());
		
		doctorDTOs = new ArrayList<DoctorDTO>();
		
		for(Iterator<EmployeeDoctor> doctorsIt = employeeDoctors.iterator(); doctorsIt.hasNext();) {
			doctorDTO = new DoctorDTO();
			
			employeeDoctor = doctorsIt.next();
			
			mapper.map(employeeDoctor.getDoctor().getContact(), doctorDTO);
			mapper.map(employeeDoctor.getDoctor(), doctorDTO);
			
			doctorDTOs.add(doctorDTO);
		}

		return doctorDTOs;
	}

	@Override
	public void saveMyDoctorsList(String username, List<Integer> doctors) throws MyDoctorsListServiceExceptionManager {
		
		user = userDao.findUserByUsername(username);
		
		employee = user.getEmployee();
		
		if(employee == null)
			throw new MyDoctorsListServiceExceptionManager(ERROR_CODE_1001);
		
		employeeDoctors = new ArrayList<EmployeeDoctor>();
		
		List<EmployeeDoctor> oldEmployeeDoctors = employeeDoctorDao.employeeDoctorsByEmployee(employee);
		
		//clear old list
		employeeDoctorDao.clearMyDoctorsList(oldEmployeeDoctors);
		
		
		//Generate new list of EmployeeDoctors
		for (Iterator<Integer> iterator = doctors.iterator(); iterator.hasNext();) {
			Integer doctorId = (Integer) iterator.next();
			
			doctor = doctorDao.findDoctorById(doctorId);
			
			if(doctor == null)
				throw new MyDoctorsListServiceExceptionManager(ERROR_CODE_1011 + " '" + doctorId + "'");
			
			employeeDoctor = new EmployeeDoctor(employee, doctor);
			
			employeeDoctors.add(employeeDoctor);
		}
		
		employeeDoctorDao.createMyDoctorsList(employeeDoctors);
	}

}
