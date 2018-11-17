/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.ProductSpecialty;
import com.dashda.data.entities.Specialty;

/**
 * @author mhanafy
 *
 */
@Repository
public class SpecialtyDaoImpl extends AbstractDao implements SpecialtyDao {

	
	public SpecialtyDaoImpl() {
		this.setDAOClass(Specialty.class);
	}

	@Override
	public Specialty findSpecialty(int specialtyId) {
		
		return (Specialty) findOne(specialtyId);
	}

	public List<Specialty> findAll(){
    Criteria criteria = getSession().createCriteria(Specialty.class);
    
		
		return criteria.list();
	}
}
