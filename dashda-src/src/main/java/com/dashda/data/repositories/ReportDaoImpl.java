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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.DoubleVisit;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ReportCoachingEmployeeSummary;
import com.dashda.data.entities.ReportCoachingSummary;
import com.dashda.data.entities.ReportUnVisit;
import com.dashda.data.entities.ReportVisitsPerEmployee;
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
	public List<Visit> generateDetails(int employeeId, Date dateTime) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("employeeByEmployeeId", "e");
		criteria.createAlias("doubleVisits", "dv");
		criteria.createAlias("serviceProvider", "sp");
		criteria.createAlias("sp.specialty", "s");
		criteria.createAlias("sp.district", "d");
		
		criteria.add(Restrictions.eq("employeeByEmployeeId.id", employeeId));
		criteria.add(Restrictions.eq("datetime", dateTime));
		
		return criteria.list();
	}

	@Override
	public List<ServiceProvider> generateTargetVisits(int employeeId) {
		Query query = getSession().createQuery(HQL_TARGET_VISITS);
		query.setParameter("employeeId", employeeId);
		List<ServiceProvider> results = query.list();
		return results;
	}

	@Override
	public List<ReportUnVisit> generateUnVisits(int employeeId) {
		Map< String, Integer> params = new HashMap<String, Integer>();
		params.put("employeeId", employeeId);
		
		List unVisits = findListNative(SQL_UNVISIT, params);
		List<ReportUnVisit> reportUnVisits = new ArrayList<>();
		for (Iterator iterator = unVisits.iterator(); iterator.hasNext();) {
			Map object = (Map) iterator.next();
			
			ReportUnVisit reportUnVisit = new ReportUnVisit((String)object.get("FIRST_NAME"), (String)object.get("LAST_NAME")
					,(String)object.get("SPECILATY"), (String)object.get("BRICK"), (BigInteger)object.get("E_COUNT")+"");
			
			reportUnVisits.add(reportUnVisit);
		}
		
		return reportUnVisits;
	}

	@Override
	public List<Visit> getVisitsList(int employeeId, Date dateFrom, Date dateTo) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("employeeByEmployeeId", "e");
		
		criteria.add(Restrictions.eq("e.id", employeeId));
		criteria.add(Restrictions.between("datetime", dateFrom, dateTo));
		
		return criteria.list();
	}

	@Override
	public Visit getvisitDetail(int visitId) {
		this.setDAOClass(Visit.class);
		
		return (Visit)findOne(visitId);
	}

	@Override
	public List<ReportVisitsPerEmployee> generateVisitsPerEmployee(int id) {
		
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.createAlias("visitsForEmployeeId", "visit", criteria.LEFT_JOIN);
		criteria.createAlias("employeesHierarchies", "hierarchies");
		criteria.createAlias("hierarchies.manager", "manager");
		criteria.createAlias("users", "user");
		
		
		ProjectionList projectionList = Projections.projectionList()
		.add(Projections.groupProperty("name"), "name")
		.add(Projections.groupProperty("id"), "id")
		.add(Projections.alias(Projections.count("visit.id"), "count"));
		
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("manager.id", id));
		criteria.add(Restrictions.eq("user.active", new Byte("1")));
		criteria.add(Restrictions.eq("user.userRole.id", 2));
		
		criteria.addOrder(Order.asc("name"));
		
		criteria.setResultTransformer(new AliasToBeanResultTransformer(ReportVisitsPerEmployee.class));
	
		return criteria.list();
	}

}
