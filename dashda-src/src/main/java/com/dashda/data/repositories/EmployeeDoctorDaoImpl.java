/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Doctor;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeDoctor;

/**
 * @author mhanafy
 *
 */
@Repository("employeeDoctorDao")
public class EmployeeDoctorDaoImpl extends AbstractDao implements EmployeeDoctorDao {

	
	@Override
	public List<EmployeeDoctor> employeeDoctorsByEmployee(Employee employee) {
		Criteria criteria = getSession().createCriteria(EmployeeDoctor.class);
		criteria.add(Restrictions.in("employee", employee));
		
		List<EmployeeDoctor> employeeDoctors = criteria.list(); 
		
		return employeeDoctors;
	}

}
