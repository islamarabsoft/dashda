/**
 * 
 */
package com.dashda.data.repositories;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.ServiceProvider;
import com.dashda.controllers.dto.visit.VisitReportInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Visit;
import com.dashda.data.entities.VisitReportComments;
import com.dashda.data.entities.VisitReportCount;
import com.dashda.data.entities.VisitReportCountByDay;
import com.dashda.data.entities.VisitReportDetailsByDay;

/**
 * @author mhanafy
 *
 */
public interface VisitDao {

	public void addVisit(Visit visit);

	public List<Visit> findVisitItemsByEmployee(Employee employee);

	public Visit findVisitByIdAndNotComplete(Integer visitId);

	public void updateVisit(Visit visit);

	public Visit findUserVisitByIdAndNotComplete(int visitId, int employeeId);

	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate);
	
	public List<Visit> findVisitInPeriodItemsByEmployee(Employee employee, Date fromDate, Date toDate, int serviceTypeId);

	public Visit findCompletedVisitByDoctorAndEmployee(ServiceProvider doctor, Employee employee);

	public List<Visit> findVisitNotComplete(ServiceProvider serviceProvider, Employee employee);
	
	public List<VisitReportCount> findVisitReportCount(Employee employee, VisitReportInputDTO input);

	public List<VisitReportCountByDay> findVisitReportCountByDay(VisitReportInputDTO input);
	
	public List<VisitReportDetailsByDay> findVisitReportDetailsByDay(VisitReportInputDTO input);
	
	public List<VisitReportComments> findVisitReportComments(Employee employee, VisitReportInputDTO input);
	
	
	public void discardAllVisitsBeforeDate(Date executionDate);
}
