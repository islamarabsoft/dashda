/**
 * 
 */
package com.dashda.data.repositories;

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
import com.dashda.data.entities.ReportVisitsPerSpecialty;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.ReportVisitsPerSpecialty;
import com.dashda.data.entities.Visit;

/**
 * @author mohamed.hanfy
 *
 */
@Repository
public class ReportSpecialtyDaoImpl extends AbstractDao implements ReportSpecialtyDao {

	@Override
	public List<ReportVisitsPerSpecialty> getVisitsPerSpecialty(Employee manager, Date dateFrom,
			Date dateTo) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("employeeByEmployeeId", "employee");
		criteria.createAlias("employee.employeesHierarchies", "hierarchies");
		criteria.createAlias("serviceProvider", "serviceProvider");
		criteria.createAlias("serviceProvider.specialty", "specialty");
	
		
		
		ProjectionList projectionList = Projections.projectionList()
		.add(Projections.groupProperty("specialty.name"), "name")
		.add(Projections.groupProperty("specialty.id"), "id")
		.add(Projections.alias(Projections.count("id"), "count"));
		
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.between("datetime", dateFrom, dateTo));
		criteria.add(Restrictions.eq("hierarchies.manager", manager));

		
		criteria.addOrder(Order.asc("specialty.name"));
		
		criteria.setResultTransformer(new AliasToBeanResultTransformer(ReportVisitsPerSpecialty.class));
	
		return criteria.list();
	}
	
	
	@Override
	public List<Visit> getVisitsPerSpecialtyDetails(Employee manager, int specialtyId, Date dateFrom, Date dateTo) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("serviceProvider", "serviceProvider");
		criteria.createAlias("serviceProvider.district", "district");
		criteria.createAlias("serviceProvider.specialty", "specialty");
		criteria.createAlias("employeeByEmployeeId", "employee");
		criteria.createAlias("employee.manager", "manager");
		criteria.createAlias("employee.employeesHierarchies", "employeeHierarchy");
		
		
		criteria.add(Restrictions.eq("employeeHierarchy.manager", manager));
		criteria.add(Restrictions.eq("specialty.id", specialtyId));
		criteria.add(Restrictions.between("datetime", dateFrom, dateTo));
		
		return criteria.list();
	}


}
