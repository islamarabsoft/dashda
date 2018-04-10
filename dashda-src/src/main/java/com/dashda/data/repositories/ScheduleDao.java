/**
 * 
 */
package com.dashda.data.repositories;

import java.sql.SQLException;
import java.util.List;

import com.dashda.data.entities.Schedule;

/**
 * @author mhanafy
 *
 */
public interface ScheduleDao {

	void addScheduleItem(Schedule schedule);

	void update(Schedule schedule) throws SQLException;

	Schedule findScheduleByID(Integer scheduleItem);

	List<Schedule> findListofScheduleItemsPendingApprovalByEmployee(int employeeId);

	List<Schedule> findListofScheduleItemsNeedAttention(int employeeId);

}