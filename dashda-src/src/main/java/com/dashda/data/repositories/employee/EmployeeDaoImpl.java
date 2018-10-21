/**
 * 
 */
package com.dashda.data.repositories.employee;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.CalendarActivity;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.EmployeeVisitFilter;
import com.dashda.data.entities.Schedule;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.AbstractDao;
import com.dashda.enums.OffVisitStatusEnum;
import com.dashda.enums.UserRoleEnum;

/**
 * @author mhanafy
 *
 */
@Repository
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	@Override
	public Employee findEmployeeByID(int id) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		
		return (Employee)criteria.uniqueResult();
	}

	@Override
	public void createEmployee(Employee employee) {
		getSession().save(employee);
		getSession().flush();
		getSession().clear();
	}

	@Override
	public List<Employee> findEmployeeByScheduleList(Set<Schedule> schedulesHashset) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("schedulesForEmployeeId", schedulesHashset));
		
		return criteria.list();
	}

	@Override
	public List findEmployeeBytypes() {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(User.class);
		
		criteria.createAlias("employee", "employee");

		ProjectionList emplProj = Projections.projectionList()
			    .add(Projections.property("employee.id"), "employeeId")			    
			    .add(Projections.property("employee.name"), "employeename")
			    .add(Projections.property("employee.manager.id"), "managerId")
			    .add(Projections.property("userRole.id"), "type");
		
		Criterion c1=Restrictions.eq("userRole.id",  UserRoleEnum.MEDICAL_REP.getValue());
		Criterion c2=Restrictions.eq("userRole.id",  UserRoleEnum.DISTRICT_MANAGER.getValue());
		Criterion c3=Restrictions.eq("userRole.id",  UserRoleEnum.REGIONAL_MANAGER.getValue());
	//	criteria.add(Restrictions.eq("userRole.id",  UserRoleEnum.MEDICAL_REP.getValue()));
	//	criteria.add(Restrictions.eq("userRole.id",  UserRoleEnum.DISTRICT_MANAGER.getValue()));
	//	criteria.add(Restrictions.eq("userRole.id",  UserRoleEnum.REGIONAL_MANAGER.getValue()));
		criteria.add(Restrictions.or(c1, c2,c3));
		
		criteria.setProjection(emplProj);
		criteria.setResultTransformer(new AliasToBeanResultTransformer(EmployeeVisitFilter.class));

		List result = criteria.list();
		
		
		return (List<EmployeeVisitFilter>)result;
	}

}
