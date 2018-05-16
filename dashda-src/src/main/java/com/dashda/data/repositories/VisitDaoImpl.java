/**
 * 
 */
package com.dashda.data.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Visit;
import com.dashda.data.entities.VisitStatus;
import com.dashda.enums.VisitStatusEnum;

import javassist.expr.NewArray;

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
		criteria.add(Restrictions.eq("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return criteria.list();
	}

	@Override
	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employee.getId()));
		criteria.add(Restrictions.in("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		criteria.add(Restrictions.between("datetime", fromDate, toDate));
		
		return criteria.list();
	}
	
	@Override
	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate,
			int serviceTypeId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("serviceProvider", "sp");
		criteria.createAlias("sp.serviceProviderType", "ty");
		
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employee.getId()));
		criteria.add(Restrictions.in("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		criteria.add(Restrictions.between("datetime", fromDate, toDate));
		if(serviceTypeId != 0)
			criteria.add(Restrictions.eq("ty.id", serviceTypeId));
		
		
		return criteria.list();
	}
	
	@Override
	public Visit findVisitByIdAndNotComplete(Integer visitId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("id", visitId));
		criteria.add(Restrictions.eq("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return (Visit)criteria.uniqueResult();
	}
	
	@Override
	public Visit findUserVisitByIdAndNotComplete(int visitId, int employeeId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("id", visitId));
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employeeId));
		criteria.add(Restrictions.eq("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return (Visit)criteria.uniqueResult();
	}

	@Override
	public void updateVisit(Visit visit) {
		getSession().update(visit);
		getSession().flush();
		getSession().clear();
		
	}

	@Override
	public Visit findCompletedVisitByDoctorAndEmployee(ServiceProvider serviceProvider, Employee employee) {
		List status = new ArrayList();
		status.add(VisitStatusEnum.PLANNED.getValue());
		status.add(VisitStatusEnum.COMPLETE.getValue());
		
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("serviceProvider", serviceProvider));
		criteria.add(Restrictions.eq("employeeByEmployeeId", employee));
		criteria.add(Restrictions.in("visitStatus.id", status));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(1);
		
		return (Visit)criteria.uniqueResult();
	}

	@Override
	public List<Visit> findVisitNotComplete(ServiceProvider serviceProvider, Employee employee) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.add(Restrictions.eq("serviceProvider", serviceProvider));
		criteria.add(Restrictions.eq("employeeByEmployeeId", employee));
		criteria.add(Restrictions.in("visitStatus.id", VisitStatusEnum.PLANNED.getValue()));
		
		return criteria.list();
	}

	@Override
	public void discardAllVisitsBeforeDate(Date executionDate) {
		Query query = getSession().createQuery("update Visit set visitStatus.id = 3 where datetime < :date and visitStatus.id = 1");
		query.setParameter("date", executionDate);
		query.executeUpdate();
		
	}



}
