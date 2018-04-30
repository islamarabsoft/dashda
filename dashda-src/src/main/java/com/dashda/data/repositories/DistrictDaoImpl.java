/**
 * 
 */
package com.dashda.data.repositories;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.District;

/**
 * @author mhanafy
 *
 */
@Repository
public class DistrictDaoImpl extends AbstractDao implements DistrictDao {

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.DistrictDao#findDistrictById(int)
	 */
	@Override
	public District findDistrictById(int districtId) {
		Criteria criteria = getSession().createCriteria(District.class);
		criteria.add(Restrictions.eq("id", districtId));
		
		return (District) criteria.uniqueResult();
	}

}
