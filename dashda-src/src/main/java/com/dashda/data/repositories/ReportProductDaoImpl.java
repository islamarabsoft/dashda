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
import com.dashda.data.entities.ProductVisit;
import com.dashda.data.entities.ReportVisitsPerProduct;
import com.dashda.data.entities.Visit;

/**
 * @author mohamed.hanfy
 *
 */
@Repository
public class ReportProductDaoImpl extends AbstractDao implements ReportProductDao {

	
	@Override
	public List getVisitsPerProduct(Employee manager, Date dateFrom, Date dateTo) {
		Criteria criteria = getSession().createCriteria(ProductVisit.class);
		criteria.createAlias("product", "product");
		criteria.createAlias("visit", "visit");
		criteria.createAlias("visit.employeeByEmployeeId", "employee");
		criteria.createAlias("employee.employeesHierarchies", "employeeHierarchy");
		
		ProjectionList projectionList = Projections.projectionList()
				.add(Projections.groupProperty("product.name"), "name")
				.add(Projections.groupProperty("product.id"), "id")
				.add(Projections.alias(Projections.countDistinct("id"), "count"));
		
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.between("visit.datetime", dateFrom, dateTo));
		criteria.add(Restrictions.eq("employeeHierarchy.manager", manager));
		
		criteria.addOrder(Order.asc("name"));
		
		criteria.setResultTransformer(new AliasToBeanResultTransformer(ReportVisitsPerProduct.class));
		
		return criteria.list();
	}

	@Override
	public List<Visit> getVisitsPerProductDetails(Employee manager, int productId) {
		Criteria criteria = getSession().createCriteria(Visit.class);
		criteria.createAlias("serviceProvider", "serviceProvider");
		criteria.createAlias("serviceProvider.district", "district");
		criteria.createAlias("serviceProvider.specialty", "specialty");
		criteria.createAlias("employeeByEmployeeId", "employee");
		criteria.createAlias("employee.manager", "manager");
		criteria.createAlias("employee.employeesHierarchies", "employeeHierarchy");
		criteria.createAlias("productVisits", "productVisits");
		criteria.createAlias("productVisits.product", "product");
		
		criteria.add(Restrictions.eq("employeeHierarchy.manager", manager));
		criteria.add(Restrictions.eq("product.id", productId));
		
		return criteria.list();
	}

}
