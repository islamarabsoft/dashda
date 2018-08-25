/**
 * 
 */
package com.dashda.data.repositories.plan;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Plan;

/**
 * @author mohamed.hanfy
 *
 */
public interface PlanDao {

	Plan getPlanByDate(Employee employee, Date startDate, Date endDate);

	void savePlan(Plan plan);

	List<Plan> plansList(Employee employee, Date date);

	Plan findById(int planId);

	void updatePlan(Plan plan);

	List<Plan> waitingForApprovalPlansList(Employee manager);

	List getCalendarActiviiesList(int employeeId, Date dateFrom, Date dateTo);

}
