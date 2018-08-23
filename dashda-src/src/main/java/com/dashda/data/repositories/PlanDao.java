/**
 * 
 */
package com.dashda.data.repositories;

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

}
