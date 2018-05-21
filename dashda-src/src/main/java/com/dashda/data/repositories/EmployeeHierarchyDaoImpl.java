/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeHierarchy;

/**
 * @author mhanafy
 *
 */
@Repository
public class EmployeeHierarchyDaoImpl extends AbstractDao implements EmployeeHierarchyDao {

	public EmployeeHierarchyDaoImpl() {
		super();
		this.setDAOClass(EmployeeHierarchy.class);
	}

	
	@Override
	public List<EmployeeHierarchy> getSubordinates(Employee manager, int higherLevelApproval) {
		Criteria criteria = getSession().createCriteria(EmployeeHierarchy.class);
		criteria.add(Restrictions.eq("manager", manager));
		criteria.add(Restrictions.le("structureLevel", higherLevelApproval));
		
		return criteria.list();
	}

}
