/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.District;
import com.dashda.data.entities.Doctor;


/**
 * @author mhanafy
 *
 */
@Repository("DoctorDao")
public class DoctorDaoImpl extends AbstractDao implements DoctorDao {

	@Override
	public List<Doctor> doctorsList(List<District> districts) {
		
		Criteria criteria = getSession().createCriteria(Doctor.class);
		criteria.add(Restrictions.in("district", districts));
		
		List<Doctor> doctors = criteria.list(); 
		
		return doctors;
	}

}
