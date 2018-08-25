/**
 * 
 */
package com.dashda.data.repositories.employee;

import java.util.List;
import java.util.Set;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Schedule;

/**
 * @author mhanafy
 *
 */
public interface EmployeeDao {

	public Employee findEmployeeByID(int managerId);

	public void createEmployee(Employee employee);

	public List<Employee> findEmployeeByScheduleList(Set<Schedule> schedulesHashset);

}
