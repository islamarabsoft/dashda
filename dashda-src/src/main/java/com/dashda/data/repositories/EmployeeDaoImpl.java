/**
 * 
 */
package com.dashda.data.repositories;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;

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

}
