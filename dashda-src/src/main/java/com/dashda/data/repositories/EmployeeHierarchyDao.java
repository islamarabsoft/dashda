/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;

/**
 * @author mhanafy
 *
 */
public interface EmployeeHierarchyDao {

	public List<EmployeeHierarchy> getSubordinates(Employee manager, int higherLevelApproval);

}
