/**
 * 
 */
package com.dashda.data.repositories.plan;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.CalendarActivity;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.OffVisit;
import com.dashda.data.entities.Plan;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.AbstractDao;
import com.dashda.enums.OffVisitStatusEnum;
import com.dashda.enums.PlanStatusEnum;
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
	public void savePlan(Plan plan) {
		create(plan);
	}

	@Override
	public void updatePlan(Plan plan) {
		getSession().saveOrUpdate(plan);
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

	@Override
	public Plan findById(int planId) {
		this.setDAOClass(Plan.class);
		return (Plan)findOne(planId);
	}

	@Override
	public List<Plan> waitingForApprovalPlansList(Employee manager) {
		Criteria criteria = getSession().createCriteria(Plan.class);
		criteria.createAlias("planStatus", "planStatus");
		criteria.createAlias("employee", "employee");
		criteria.createAlias("employee.employeesHierarchies", "employeesHierarchies");
		
		criteria.add(Restrictions.eq("employeesHierarchies.manager", manager));
		criteria.add(Restrictions.eq("planStatus.id", PlanStatusEnum.SENT_FOR_APPROVAL.getValue()));
		
		criteria.addOrder(Order.desc("startDate"));
		return criteria.list();
	}

	@Override
	public List getCalendarActiviiesList(int employeeId, Date dateFrom, Date dateTo) {
		
		Criteria schedule = getSession().createCriteria(Schedule.class);
		
		schedule.createAlias("serviceProvider", "serviceProvider");
		schedule.createAlias("serviceProvider.specialty", "specialty");
		schedule.createAlias("serviceProvider.district", "district");
		schedule.createAlias("scheduleStatus", "scheduleStatus");
		schedule.createAlias("visit", "visit", JoinType.LEFT_OUTER_JOIN);
		schedule.createAlias("employeeByEmployeeId", "employee");
		
		ProjectionList scheduleProj = Projections.projectionList()
			    .add(Projections.property("serviceProvider.enName"), "event")
			    .add(Projections.property("specialty.name"), "specialty")
			    .add(Projections.property("district.enName"), "district")
			    .add(Projections.property("scheduleStatus.id"), "statusId")
			    .add(Projections.property("scheduleStatus.name"), "status")
			    .add(Projections.property("datetime"), "date")
			    .add(Projections.property("visit.comment"), "comment")
				.add(Projections.property("visit.visitStatus.id"), "visitStatusId");
		
		
		schedule.add(Restrictions.eq("employee.id", employeeId));
		schedule.add(Restrictions.between("datetime", dateFrom, dateTo));
		
		schedule.setProjection(scheduleProj);
		schedule.addOrder(Order.desc("datetime"));
		
		Criteria offVisit = getSession().createCriteria(OffVisit.class);
		
		offVisit.createAlias("offVisitReason", "offVisitReason");
		offVisit.createAlias("offVisitStatus", "offVisitStatus");
		offVisit.createAlias("employee", "employee");
		
		ProjectionList offVisitProj = Projections.projectionList()
			    .add(Projections.property("offVisitReason.name"), "event")
			    .add(Projections.property("offVisitStatus.id"), "statusId")
			    .add(Projections.property("offVisitStatus.name"), "status")
			    .add(Projections.property("dateTime"), "date")
			    .add(Projections.property("comment"), "comment");
		
		offVisit.add(Restrictions.eq("employee.id", employeeId));
		offVisit.add(Restrictions.eq("offVisitStatus.id", OffVisitStatusEnum.ACCEPTED.getValue()));
		offVisit.add(Restrictions.between("dateTime", dateFrom, dateTo));
		
		offVisit.setProjection(offVisitProj);
		offVisit.addOrder(Order.desc("dateTime"));
		
		schedule.setResultTransformer(new AliasToBeanResultTransformer(CalendarActivity.class));
		offVisit.setResultTransformer(new AliasToBeanResultTransformer(CalendarActivity.class));
		
		List result = schedule.list();
		List result2 = offVisit.list();
		result.addAll(result2);
		
		
		return (List<CalendarActivity>)result;
	}

}
