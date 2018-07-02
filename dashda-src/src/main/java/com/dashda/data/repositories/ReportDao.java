/**
 * 
 */
package com.dashda.data.repositories;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.dashda.data.entities.DoubleVisit;
import com.dashda.data.entities.ReportCoachingEmployeeSummary;
import com.dashda.data.entities.ReportCoachingSummary;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Visit;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportDao {

	List<ReportCoachingSummary> generateSummary(List<Integer> employeeIds, String dateFrom, String dateTo)throws ParseException;

	List<ReportCoachingEmployeeSummary> generateEmployeeSummary(int employeeId, String dateFrom, String dateTo)throws ParseException;

	List<DoubleVisit> generateDetails(int employeeId, Date convertToDate);

	List<ServiceProvider> generateTargetVisits(int employeeId);

}
