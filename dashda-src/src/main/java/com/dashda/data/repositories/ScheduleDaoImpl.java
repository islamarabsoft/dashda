/**
 * 
 */
package com.dashda.data.repositories;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Schedule;


/**
 * @author mhanafy
 *
 */

@Repository
public class ScheduleDaoImpl extends AbstractDao implements ScheduleDao {

	@Override
	public void addScheduleItem(Schedule schedule) {
		
		getSession().save(schedule);
		getSession().flush();
		getSession().clear();
		
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

}
