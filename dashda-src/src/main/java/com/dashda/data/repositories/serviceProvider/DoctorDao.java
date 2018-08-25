/**
 * 
 */
package com.dashda.data.repositories.serviceProvider;

import java.util.List;

import com.dashda.data.entities.District;
import com.dashda.data.entities.ServiceProvider;

/**
 * @author mhanafy
 *
 */
public interface DoctorDao {

	public List<ServiceProvider> doctorsList(List<District> districts, int serviceProviderType);

	public ServiceProvider findDoctorById(Integer doctorId);

}
