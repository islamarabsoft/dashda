/**SpecialtyDao
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import com.dashda.data.entities.Specialty;

/**
 * @author mhanafy
 *
 */
public interface SpecialtyDao {

	Specialty findSpecialty(int specialtyId);

	List<Specialty> findAll();

}
