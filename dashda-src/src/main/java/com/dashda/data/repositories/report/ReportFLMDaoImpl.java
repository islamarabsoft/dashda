/**
 * 
 */
package com.dashda.data.repositories.report;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ProductVisit;
import com.dashda.data.entities.ReportVisitsPerEmployee;
import com.dashda.data.entities.ReportVisitsPerFLM;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.AbstractDao;

/**
 * @author mohamed.hanfy
 *
 */
@Repository
public class ReportFLMDaoImpl extends AbstractDao implements ReportFLMDao {


	@Override
	public List<ReportVisitsPerFLM> getVisitsPerFLM(Employee manager, Date dateFrom, Date dateTo) {
		
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.createAlias("visitsForEmployeeId", "visit", criteria.LEFT_JOIN);
		criteria.createAlias("employeesHierarchies", "hierarchies");
		criteria.createAlias("manager", "manager");
		criteria.createAlias("manager.users", "user");
		
		
		ProjectionList projectionList = Projections.projectionList()
		.add(Projections.groupProperty("manager.name"), "name")
		.add(Projections.groupProperty("manager.id"), "id")
		.add(Projections.alias(Projections.count("visit.id"), "count"));
		
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.between("visit.datetime", dateFrom, dateTo));
		criteria.add(Restrictions.eq("hierarchies.manager", manager));
		criteria.add(Restrictions.eq("user.active", new Byte("1")));
		criteria.add(Restrictions.eq("user.userRole.id", 3));
		
		criteria.addOrder(Order.asc("manager.name"));
		
		criteria.setResultTransformer(new AliasToBeanResultTransformer(ReportVisitsPerFLM.class));
	
		return criteria.list();
	}

	@Override
	public List<Visit> getVisitsPerFLMDetails(Employee manager, int flm, Date dateFrom, Date dateTo) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("serviceProvider", "serviceProvider");
		criteria.createAlias("serviceProvider.district", "district");
		criteria.createAlias("serviceProvider.specialty", "specialty");
		criteria.createAlias("employeeByEmployeeId", "employee");
		criteria.createAlias("employee.manager", "manager");
		criteria.createAlias("employee.employeesHierarchies", "employeeHierarchy");
		
		
		criteria.add(Restrictions.eq("employeeHierarchy.manager", manager));
		criteria.add(Restrictions.eq("manager.id", flm));
		criteria.add(Restrictions.between("datetime", dateFrom, dateTo));
		
		return criteria.list();
	}

}
