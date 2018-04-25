/**
 * 
 */
package com.dashda.data.repositories;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Visit;

/**
 * @author mhanafy
 *
 */
@Repository
public class VisitDaoImpl extends AbstractDao implements VisitDao {

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.VisitDao#addVisit(com.dashda.data.entities.Visit)
	 */
	@Override
	public void addVisit(Visit visit) {
		getSession().save(visit);
		getSession().flush();
		getSession().clear();

	}

	@Override
	public List<Visit> findVisitItemsByEmployee(Employee employee) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employee.getId()));
		criteria.add(Restrictions.isNull("completed"));
		
		return criteria.list();
	}

	@Override
	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employee.getId()));
		criteria.add(Restrictions.isNull("completed"));
		criteria.add(Restrictions.between("datetime", fromDate, toDate));
		
		return criteria.list();
	}
	@Override
	public Visit findVisitByIdAndNotComplete(Integer visitId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("id", visitId));
		criteria.add(Restrictions.isNull("completed"));
		
		return (Visit)criteria.uniqueResult();
	}
	
	@Override
	public Visit findUserVisitByIdAndNotComplete(Integer visitId, Integer employeeId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("id", visitId));
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employeeId));
		criteria.add(Restrictions.isNull("completed"));
		
		return (Visit)criteria.uniqueResult();
	}

	@Override
	public void updateVisit(Visit visit) {
		getSession().update(visit);
		getSession().flush();
		getSession().clear();
		
	}

}
