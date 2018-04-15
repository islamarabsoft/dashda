/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.DoctorDTO;
import com.dashda.data.entities.Contact;
import com.dashda.data.entities.District;
import com.dashda.data.entities.Doctor;
import com.dashda.data.entities.EmployeesCoveredDistrict;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.exception.DoctorServiceExceptionManager;

import ch.qos.logback.core.util.COWArrayList;

/**
 * @author mhanafy
 *
 */
@Service("DoctorsListService")
public class DoctorsListServiceImpl extends ServicesManager implements DoctorsListService {

	/* (non-Javadoc)
	 * @see com.dashda.service.components.DoctorsListService#serviceList()
	 */
	
	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	UserDao userDao;

	private List<District> districts;
	
	private Doctor doctor;
	
	private DoctorDTO doctorDTO;
	
	private List<DoctorDTO> doctorsDTO;
	
	private Contact contact;

	
	@Override
	public List<DoctorDTO> doctorsList(String username) throws DoctorServiceExceptionManager {
		
		User user = userDao.findUserByUsername(username);
		districts = new ArrayList<District>();

		
		if(user.getEmployee() == null)
			throw new DoctorServiceExceptionManager(ERROR_CODE_1001);
		if(user.getEmployee().getEmployeesCoveredDistricts().size() == 0)
			throw new DoctorServiceExceptionManager(ERROR_CODE_1002);
		
		for(Iterator<EmployeesCoveredDistrict> employeesCoveredDistrictIt = 
				user.getEmployee().getEmployeesCoveredDistricts().iterator(); 
				employeesCoveredDistrictIt.hasNext();) {
			districts.add(((EmployeesCoveredDistrict)employeesCoveredDistrictIt.next()).getDistrict());
		}
		
		List<Doctor> doctors = doctorDao.doctorsList(districts);
		
		if(doctors.size() == 0)
			throw new DoctorServiceExceptionManager(ERROR_CODE_1010);
		
		doctorsDTO = new ArrayList<DoctorDTO>();
		
		for(Iterator<Doctor> doctorsIt = doctors.iterator(); doctorsIt.hasNext();) {
			doctor = (Doctor)doctorsIt.next();
			contact = doctor.getContact();
			
			doctorDTO = new DoctorDTO();
			
			mapper.map(doctor.getContact(), doctorDTO);
			mapper.map(doctor, doctorDTO);
			
			
			doctorsDTO.add(doctorDTO);
		}

		
		return doctorsDTO;
	}

}
