/**
 * 
 */
package com.dashda.data.repositories;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Visit;

/**
 * @author mhanafy
 *
 */
public interface VisitDao {

	public void addVisit(Visit visit);

	public List<Visit> findVisitItemsByEmployee(Employee employee);

	public Visit findVisitByIdAndNotComplete(Integer visitId);

	public void updateVisit(Visit visit);

	Visit findUserVisitByIdAndNotComplete(Integer visitId, Integer employeeId);

	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate);
}
