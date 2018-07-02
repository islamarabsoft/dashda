/**
 * 
 */
package com.dashda.data.repositories;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.ObjDoubleConsumer;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.DoubleVisit;
import com.dashda.data.entities.ReportCoachingEmployeeSummary;
import com.dashda.data.entities.ReportCoachingSummary;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.Specialty;
import com.dashda.data.entities.Visit;
import com.dashda.utilities.DateUtilities;

/**
 * @author mohamed.hanfy
 *
 */
@Repository
public class ReportDaoImpl extends AbstractDao implements ReportDao, NamedQueries {

	@Override
	public List<ReportCoachingSummary> generateSummary(List<Integer> employeeIds, String dateFrom, String dateTo) throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> arrayParams = new HashMap<String, Object>();
		
		params.put("dateFrom", DateUtilities.convertToDate(dateFrom, DateUtilities.DATE_FORMATE_PATTERN));
		params.put("dateTo", DateUtilities.convertToDate(dateTo, DateUtilities.DATE_FORMATE_PATTERN));
		
		arrayParams.put("managerId", employeeIds.toArray());
		
		List list = findListNative(SQL_COACHING_SUMARRY_GROUPED_BY_MANAGER_COUNT, params, arrayParams);
		
		List<ReportCoachingSummary> reportCoachingSummaries = new ArrayList<>();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map object = (Map) iterator.next();
			ReportCoachingSummary reportCoachingSummary = new ReportCoachingSummary();
			
			reportCoachingSummary.setEmployeeId((int)object.get("ID"));
			reportCoachingSummary.setCount(((BigInteger)object.get("E_COUNT")).intValue());
			reportCoachingSummary.setEmployeeName((String)object.get("NAME"));
			
			reportCoachingSummaries.add(reportCoachingSummary);
		}
		return reportCoachingSummaries;
	}

	@Override
	public List<ReportCoachingEmployeeSummary> generateEmployeeSummary(int managerId, String dateFrom, String dateTo)
			throws ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("managerId", managerId);
		params.put("dateFrom", DateUtilities.convertToDate(dateFrom, DateUtilities.DATE_FORMATE_PATTERN));
		params.put("dateTo", DateUtilities.convertToDate(dateTo, DateUtilities.DATE_FORMATE_PATTERN));
		
		List list = findListNative(SQL_REPORT_SUMMARY_GROUPED_BY_EMPLOYEE_DATE_COUNT, params);
		
		List<ReportCoachingEmployeeSummary> reportCoachingEmployeeSummaries = new ArrayList<>();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map object = (Map) iterator.next();
			ReportCoachingEmployeeSummary reportCoachingEmployeeSummary = new ReportCoachingEmployeeSummary();
			
			reportCoachingEmployeeSummary.setEmployeeId((int)object.get("ID"));
			reportCoachingEmployeeSummary.setCount(((BigInteger)object.get("E_COUNT")).intValue());
			reportCoachingEmployeeSummary.setEmployeeName((String)object.get("NAME"));
			reportCoachingEmployeeSummary.setDateTime(DateUtilities.dateFormate((Timestamp)object.get("DATETIME")+""));
			
			
			reportCoachingEmployeeSummaries.add(reportCoachingEmployeeSummary);
		}
		
		return reportCoachingEmployeeSummaries;
	}

	@Override
	public List<DoubleVisit> generateDetails(int employeeId, Date dateTime) {
		Criteria criteria = getSession().createCriteria(DoubleVisit.class);
		criteria.createAlias("employee", "e");
		criteria.createAlias("visit", "v");
		criteria.createAlias("v.serviceProvider", "sp");
		criteria.createAlias("sp.specialty", "s");
		criteria.createAlias("sp.district", "d");
		
		criteria.add(Restrictions.eq("employee.id", employeeId));
		criteria.add(Restrictions.eq("v.datetime", dateTime));
		
		return criteria.list();
	}

	@Override
	public List<ServiceProvider> generateTargetVisits(int employeeId) {
		Query query = getSession().createQuery(HQL_TARGET_VISITS);
		query.setParameter("employeeId", employeeId);
		List<ServiceProvider> results = query.list();
		return results;
	}

}
