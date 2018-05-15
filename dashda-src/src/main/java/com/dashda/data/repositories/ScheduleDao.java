/**
 * 
 */
package com.dashda.data.repositories;

import java.sql.SQLException;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.ServiceProvider;

/**
 * @author mhanafy
 *
 */
public interface ScheduleDao {

	void saveScheduleItem(Schedule schedule);

	void update(Schedule schedule);

	Schedule findScheduleByID(Integer scheduleItem);

	List<Schedule> findListofScheduleItemsPendingApprovalByEmployee(int employeeId);

	List<Schedule> findListofScheduleItemsNeedAttention(int employeeId);

	Schedule findPendingApprovalScheduleForManagerByID(Integer scheduleItemId, int managerId);

	public void deleteScheduleItem(Schedule schedule);

	List<Schedule> findScheduleItemNotApproved(ServiceProvider serviceProvider, Employee employee);

}