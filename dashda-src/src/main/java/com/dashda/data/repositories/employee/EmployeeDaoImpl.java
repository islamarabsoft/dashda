/**
 * 
 */
package com.dashda.data.repositories.employee;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Schedule;
import com.dashda.data.repositories.AbstractDao;

/**
 * @author mhanafy
 *
 */
@Repository
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	@Override
	public Employee findEmployeeByID(int id) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		
		return (Employee)criteria.uniqueResult();
	}

	@Override
	public void createEmployee(Employee employee) {
		getSession().save(employee);
		getSession().flush();
		getSession().clear();
	}

	@Override
	public List<Employee> findEmployeeByScheduleList(Set<Schedule> schedulesHashset) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("schedulesForEmployeeId", schedulesHashset));
		
		return criteria.list();
	}

}
