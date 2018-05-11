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
import com.dashda.data.entities.OffVisit;
import com.dashda.enums.OffVisitStatusEnum;

/**
 * @author mhanafy
 *
 */
@Repository
public class OffVisitDaoImpl extends AbstractDao implements OffVisitDao {


	public OffVisitDaoImpl() {
		this.setDAOClass(OffVisit.class);		
	}


	@Override
	public OffVisit findOffVisit(Employee employee, Date date, int reasonId) {
		Criteria criteria = getSession().createCriteria(OffVisit.class);
		criteria.add(Restrictions.eq("employee", employee));
		criteria.add(Restrictions.eq("dateTime", date));
		criteria.add(Restrictions.eq("offVisitReason.id", reasonId));
		
		return (OffVisit)criteria.uniqueResult();
	}

	@Override
	public void createOffVisit(OffVisit offVisit) {
		save(offVisit);
	}


	@Override
	public OffVisit findOffVisit(int offVisitId) {
		Criteria criteria = getSession().createCriteria(OffVisit.class);
		criteria.add(Restrictions.eq("id", offVisitId));

		
		return (OffVisit)criteria.uniqueResult();
	}


	@Override
	public void removeOffVisit(OffVisit offVisit) {
		delete(offVisit);
	}


	@Override
	public List<OffVisit> findPendingApproval(int managerId) {
		Criteria criteria = getSession().createCriteria(OffVisit.class);
		criteria.add(Restrictions.eq("offVisitStatus.id", OffVisitStatusEnum.PENDING_APPROVAL.getValue()));
		criteria.add(Restrictions.eq("manager.id", managerId));
		
		return criteria.list();
	}


	@Override
	public List<OffVisit> findOffVisitsAfterToday(Employee employee) {
		Criteria criteria = getSession().createCriteria(OffVisit.class);
		criteria.add(Restrictions.gt("dateTime", new Date()));
		criteria.add(Restrictions.eq("employee", employee));
		
		return criteria.list();
	}
}
