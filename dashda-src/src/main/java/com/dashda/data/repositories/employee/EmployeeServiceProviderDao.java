/**
 * 
 */
package com.dashda.data.repositories.employee;

import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeServiceProvider;

/**
 * @author mhanafy
 *
 */
public interface EmployeeServiceProviderDao {


	public List<EmployeeServiceProvider> employeeServiceProviderByEmployee(Employee employee, int serviceProviderTypeId);
	public List<EmployeeServiceProvider> employeeServiceProviderByEmployee(Employee employee);

	public void clearMyServiceProvidersList(List<EmployeeServiceProvider> employeeServiceProviders);

	void createMyServiceProvidersList(List<EmployeeServiceProvider> employeeServiceProviders);

	EmployeeServiceProvider findEmployeeServiceProviderByEmployeeIdAndServiceProviderId(Integer employeeId, Integer ServiceProviderId);

	public void addServiceProviderToMyList(EmployeeServiceProvider employeeServiceProvider);

	public void removeEmployeeServiceProviderById(int assignedId);

	public EmployeeServiceProvider findEmployeeServiceProviderById(int assignedId);

	public void removeEmployeeServiceProvider(EmployeeServiceProvider employeeServiceProvider);
}
