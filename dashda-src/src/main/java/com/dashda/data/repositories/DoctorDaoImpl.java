/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.District;
import com.dashda.data.entities.ServiceProvider;


/**
 * @author mhanafy
 *
 */
@Repository("DoctorDao")
public class DoctorDaoImpl extends AbstractDao implements DoctorDao {

	@Override
	public List<ServiceProvider> doctorsList(List<District> districts, int serviceProviderType) {
		
		Criteria criteria = getSession().createCriteria(ServiceProvider.class);
		criteria.add(Restrictions.in("district", districts));
		if(serviceProviderType != 0)
			criteria.add(Restrictions.eq("serviceProviderType.id", serviceProviderType));
		
		criteria.addOrder(Order.asc("speciality"));
		criteria.addOrder(Order.asc("enName"));
		
		List<ServiceProvider> doctors = criteria.list(); 
		
		return doctors;
	}

	@Override
	public ServiceProvider findDoctorById(Integer doctorId) {
		Criteria criteria = getSession().createCriteria(ServiceProvider.class);
		criteria.add(Restrictions.in("id", doctorId));
		
		ServiceProvider doctor = (ServiceProvider)criteria.uniqueResult(); 
		
		return doctor;
	}

}
