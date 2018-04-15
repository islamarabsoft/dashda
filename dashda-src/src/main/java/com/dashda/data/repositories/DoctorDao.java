/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import com.dashda.data.entities.District;
import com.dashda.data.entities.Doctor;

/**
 * @author mhanafy
 *
 */
public interface DoctorDao {

	public List<Doctor> doctorsList(List<District> districts);

	public Doctor findDoctorById(Integer doctorId);

}
