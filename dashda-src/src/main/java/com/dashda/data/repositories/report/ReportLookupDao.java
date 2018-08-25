/**
 * 
 */
package com.dashda.data.repositories.report;

import java.util.List;

import com.dashda.data.entities.District;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Specialty;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportLookupDao {

	List<Employee> findSubordinates(Employee manager);
	
	List<Employee> findSubordinatesWithoutLowLevel(Employee manager);

	List<Employee> findSubordinatesLowLevel(Employee employee);
	
	List<District> findAssignedDistrict(int id);
	
	List<Specialty> findAssignedSpecialty(int id);
	
	List<ServiceProvider> findAssignedServiceProvider(int id);

	
}
