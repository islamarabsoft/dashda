/**
 * 
 */
package com.dashda.data.repositories.employee;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeServiceProvider;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.ServiceProviderType;
import com.dashda.data.repositories.AbstractDao;


/**
 * @author mhanafy
 *
 */
@Repository("employeeServiceProviderDao")
public class EmployeeServiceProviderDaoImpl extends AbstractDao implements EmployeeServiceProviderDao {

	
	
	/**
	 * 
	 */
	public EmployeeServiceProviderDaoImpl() {
		this.setDAOClass(EmployeeServiceProvider.class);
	}

	@Override
	public List<EmployeeServiceProvider> employeeServiceProviderByEmployee(Employee employee, int serviceProviderTypeId) {
		Criteria criteria = getSession().createCriteria(EmployeeServiceProvider.class);
		criteria.createAlias("serviceProvider", "sp");
		criteria.createAlias("sp.serviceProviderType", "ty");
		
		criteria.add(Restrictions.in("employee", employee));
		if(serviceProviderTypeId != 0)
			criteria.add(Restrictions.eq("ty.id", serviceProviderTypeId));
	
		criteria.setMaxResults(100);
		return criteria.list();
	}

	@Override
	public void clearMyServiceProvidersList(List<EmployeeServiceProvider> employeeServiceProviders) {

		EmployeeServiceProvider employeeServiceProvider;
		
		for (Iterator<EmployeeServiceProvider> iterator = employeeServiceProviders.iterator(); iterator.hasNext();) {
			
			employeeServiceProvider = (EmployeeServiceProvider) iterator.next();
		
			getSession().delete(employeeServiceProvider);
		
		}
		
	}
	
	@Override
	public void createMyServiceProvidersList(List<EmployeeServiceProvider> employeeServiceProviders) {

		EmployeeServiceProvider employeeServiceProvider;
		
		for (Iterator<EmployeeServiceProvider> iterator = employeeServiceProviders.iterator(); iterator.hasNext();) {
			
			employeeServiceProvider = (EmployeeServiceProvider) iterator.next();
			
			getSession().save(employeeServiceProvider);
			getSession().flush();
			getSession().clear();
			
		}
	}
	
	@Override
	public EmployeeServiceProvider findEmployeeServiceProviderByEmployeeIdAndServiceProviderId(Integer employeeId, Integer serviceProviderId) {
		Criteria criteria = getSession().createCriteria(EmployeeServiceProvider.class);
		criteria.add(Restrictions.in("employee.id", employeeId));
		criteria.add(Restrictions.in("serviceProvider.id", serviceProviderId));
		
		
		return (EmployeeServiceProvider)criteria.uniqueResult();
	}

	@Override
	public void addServiceProviderToMyList(EmployeeServiceProvider employeeServiceProvider) {
		save(employeeServiceProvider);
		
	}

	@Override
	public void removeEmployeeServiceProviderById(int assignedId) {
		getSession().delete(new EmployeeServiceProvider(assignedId));
	}

	@Override
	public EmployeeServiceProvider findEmployeeServiceProviderById(int assignedId) {
		return (EmployeeServiceProvider)findOne(assignedId);
	}

	@Override
	public void removeEmployeeServiceProvider(EmployeeServiceProvider employeeServiceProvider) {
		delete(employeeServiceProvider);
		
	}

	@Override
	public List<EmployeeServiceProvider> employeeServiceProviderByEmployee(Employee employee) {
		Criteria criteria = getSession().createCriteria(EmployeeServiceProvider.class);
		
		criteria.add(Restrictions.in("employee", employee));
	
		criteria.setMaxResults(100);
		return criteria.list();
	}

}
