/**
 * 
 */
package com.dashda.data.repositories.employee;

import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;

/**
 * @author mhanafy
 *
 */
public interface EmployeeHierarchyDao {

	public List<EmployeeHierarchy> getSubordinates(Employee manager, int higherLevelApproval);

	public List<EmployeeHierarchy> getManagers(Employee employee);

	public EmployeeHierarchy employeeHierarchy(Employee employee, Employee manager);

}
