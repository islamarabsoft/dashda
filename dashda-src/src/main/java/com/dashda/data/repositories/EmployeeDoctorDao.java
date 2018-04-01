/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeDoctor;

/**
 * @author mhanafy
 *
 */
public interface EmployeeDoctorDao {


	public List<EmployeeDoctor> employeeDoctorsByEmployee(Employee employee);
}
