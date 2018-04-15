/**
 * 
 */
package com.dashda.data.repositories;

import com.dashda.data.entities.Employee;

/**
 * @author mhanafy
 *
 */
public interface EmployeeDao {

	public Employee findEmployeeByID(int managerId);

	public void createEmployee(Employee employee);

}
