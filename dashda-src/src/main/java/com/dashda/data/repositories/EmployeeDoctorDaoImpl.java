/**
 * 
 */
package com.dashda.data.repositories;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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

	@Override
	public void clearMyDoctorsList(List<EmployeeDoctor> employeeDoctors) {

		EmployeeDoctor employeeDoctor;
		
		for (Iterator<EmployeeDoctor> iterator = employeeDoctors.iterator(); iterator.hasNext();) {
			
			employeeDoctor = (EmployeeDoctor) iterator.next();
		
			getSession().delete(employeeDoctor);
		
		}
		
	}
	
	@Override
	public void createMyDoctorsList(List<EmployeeDoctor> employeeDoctors) {

		EmployeeDoctor employeeDoctor;
		
		for (Iterator<EmployeeDoctor> iterator = employeeDoctors.iterator(); iterator.hasNext();) {
			
			employeeDoctor = (EmployeeDoctor) iterator.next();
			
			getSession().save(employeeDoctor);
			getSession().flush();
			getSession().clear();
			
		}
	}
	
	@Override
	public EmployeeDoctor findEmployeeDoctorByEmployeeIdAndDoctorId(Integer employeeId, Integer doctorId) {
		Criteria criteria = getSession().createCriteria(EmployeeDoctor.class);
		criteria.add(Restrictions.in("employee.id", employeeId));
		criteria.add(Restrictions.in("doctor.id", doctorId));
		
		
		return (EmployeeDoctor)criteria.uniqueResult();
	}

}
