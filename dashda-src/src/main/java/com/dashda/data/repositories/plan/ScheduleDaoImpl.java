/**
 * 
 */
package com.dashda.data.repositories.plan;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Plan;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.repositories.AbstractDao;
import com.dashda.enums.ScheduleStatusEnum;


/**
 * @author mhanafy
 *
 */

@Repository
public class ScheduleDaoImpl extends AbstractDao implements ScheduleDao {

	@Override
	public void saveScheduleItem(Schedule schedule) {
		save(schedule);
	}

	@Override
	public void update(Schedule schedule){
		getSession().saveOrUpdate(schedule);
		
	}

	@Override
	public Schedule findScheduleByID(Integer scheduleItemId) {
		Criteria criteria = getSession().createCriteria(Schedule.class);
		criteria.add(Restrictions.eq("id", scheduleItemId));
		
		return (Schedule)criteria.uniqueResult();
	}
	
	@Override
	public Schedule findPendingApprovalScheduleForManagerByID(Integer scheduleItemId, int managerId) {
		Criteria criteria = getSession().createCriteria(Schedule.class);
		criteria.add(Restrictions.eq("id", scheduleItemId));
		criteria.add(Restrictions.eq("scheduleStatus.id", 1));
		criteria.add(Restrictions.eq("employeeByManagerId.id", managerId));
		
		return (Schedule)criteria.uniqueResult();
	}

	@Override
	public List<Schedule> findListofScheduleItemsPendingApprovalByEmployee(int employeeId) {
		Criteria criteria = getSession().createCriteria(Schedule.class);
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employeeId));
		criteria.add(Restrictions.eq("scheduleStatus.id", 1));
		
		return criteria.list();
	}

	@Override
	public List<Schedule> findListofScheduleItemsNeedAttention(int managerId) {
		Criteria criteria = getSession().createCriteria(Schedule.class);
		criteria.add(Restrictions.eq("employeeByManagerId.id", managerId));
		criteria.add(Restrictions.eq("scheduleStatus.id", 1));
		
		return criteria.list();
	}

	@Override
	public void deleteScheduleItem(Schedule schedule) {
		getSession().delete(schedule);
		
	}

	@Override
	public List<Schedule> findScheduleItemNotApproved(ServiceProvider serviceProvider, Employee employee) {
		Criteria criteria = getSession().createCriteria(Schedule.class);
		criteria.add(Restrictions.eq("serviceProvider", serviceProvider));
		criteria.add(Restrictions.eq("employeeByEmployeeId", employee));
		criteria.add(Restrictions.eq("scheduleStatus.id", ScheduleStatusEnum.PENDING_APPROVAL.getValue()));
		
		return criteria.list();
	}

	@Override
	public List<Schedule> findListOfSubordinateSchedules(List<Employee> subordinates) {
		Criteria criteria = getSession().createCriteria(Schedule.class);
		criteria.add(Restrictions.in("employeeByEmployeeId", subordinates));
		criteria.add(Restrictions.eq("scheduleStatus.id", 1));
		
		return criteria.list();
	}

	@Override
	public List<Schedule> findListofscheduleItemsByPlan(Plan plan) {
		Criteria criteria = getSession().createCriteria(Schedule.class);
		criteria.createAlias("serviceProvider", "serviceProvider");
		criteria.createAlias("serviceProvider.district", "district");
		criteria.createAlias("serviceProvider.specialty", "specialty");
		criteria.createAlias("scheduleStatus", "scheduleStatus");
		criteria.createAlias("visit", "visit", JoinType.LEFT_OUTER_JOIN);
		
		criteria.add(Restrictions.eq("plan", plan));
		
		return criteria.list();
	}

}
