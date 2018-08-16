/**
 * 
 */
package com.dashda.data.repositories;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Plan;
import com.google.appengine.api.search.query.QueryParser.restriction_return;

/**
 * @author mohamed.hanfy
 *
 */
@Repository
public class PlanDaoImpl extends AbstractDao implements PlanDao {

	@Override
	public Plan getPlanByDate(Employee employee, Date startDate, Date endDate) {
		Criteria criteria = getSession().createCriteria(Plan.class);
		criteria.add(Restrictions.eq("employee", employee));
		criteria.add(Restrictions.le("startDate", endDate));
		criteria.add(Restrictions.ge("endDate", startDate));
		
		return (Plan)criteria.uniqueResult();
	}

	@Override
	public void createPlan(Plan plan) {
		create(plan);
	}

	@Override
	public List<Plan> plansList(Employee employee, Date date) {
		Criteria criteria = getSession().createCriteria(Plan.class);
		criteria.createAlias("planStatus", "planStatus");
		
		criteria.add(Restrictions.eq("employee", employee));
		criteria.add(Restrictions.ge("startDate", date));
		
		criteria.addOrder(Order.desc("startDate"));
		return criteria.list();
	}

}
