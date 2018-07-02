/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.District;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Specialty;

/**
 * @author mohamed.hanfy
 *
 */
@Repository
public class ReportLookupDaoImpl extends AbstractDao implements ReportLookupDao, NamedQueries {
	

	public ReportLookupDaoImpl() {
	}

	@Override
	public List<Employee> findSubordinates(Employee manager) {
		Query query = getSession().createQuery(HQL_SUBORDINATES);
		query.setParameter("managerId", manager.getId());
		List<Employee> results = query.list();
		return results;
	}

	@Override
	public List<District> findAssignedDistrict(int id) {
		Query query = getSession().createQuery(HQL_ASSIGNED_DISTRICT);
		query.setParameter("id", id);
		List<District> results = query.list();
		return results;
	}

	@Override
	public List<Specialty> findAssignedSpecialty(int id) {
		Query query = getSession().createQuery(HQL_ASSIGNED_SPECIALTY);
		query.setParameter("id", id);
		List<Specialty> results = query.list();
		return results;
	}
	
	@Override
	public List<ServiceProvider> findAssignedServiceProvider(int id) {
		Query query = getSession().createQuery(HQL_ASSIGNED_SERVICE_PROVIDER);
		query.setParameter("id", id);
		List<ServiceProvider> results = query.list();
		return results;
	}

	@Override
	public List<Employee> findSubordinatesWithoutLowLevel(Employee manager) {
		Query query = getSession().createQuery(HQL_SUBORDINATES_WITHOUT_LOW_LEVEL);
		query.setParameter("managerId", manager.getId());
		List<Employee> results = query.list();
		return results;
	}

	@Override
	public List<Employee> findSubordinatesLowLevel(Employee employee) {
		Query query = getSession().createQuery(HQL_SUBORDINATES_LOW_LEVEL);
		query.setParameter("managerId", employee.getId());
		List<Employee> results = query.list();
		return results;
	}
	
	
}
