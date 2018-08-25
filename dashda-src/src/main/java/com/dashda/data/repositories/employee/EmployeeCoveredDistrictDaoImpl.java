/**
 * 
 */
package com.dashda.data.repositories.employee;

import org.springframework.stereotype.Repository;

import com.dashda.data.entities.EmployeesCoveredDistrict;
import com.dashda.data.repositories.AbstractDao;

/**
 * @author mhanafy
 *
 */
@Repository
public class EmployeeCoveredDistrictDaoImpl extends AbstractDao implements EmployeeCoveredDistrictDao {

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.EmployeeCoveredDistrictDao#save(com.dashda.data.entities.EmployeesCoveredDistrict)
	 */
	public void assignDistrictToEmployee(EmployeesCoveredDistrict coveredDistrict) {
		save(coveredDistrict);
	}

}
