/**
 * 
 */
package com.dashda.data.repositories;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ServiceProvider;
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
	public void update(Schedule schedule) throws SQLException {
		getSession().update(schedule);
		
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

}
