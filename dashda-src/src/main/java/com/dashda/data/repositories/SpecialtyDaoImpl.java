/**
 * 
 */
package com.dashda.data.repositories;

import org.springframework.stereotype.Repository;

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

}
