/**
 * 
 */
package com.dashda.data.repositories.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dashda.data.entities.UserRole;
import com.dashda.data.repositories.AbstractDao;

/**
 * @author mhanafy
 *
 */
@Repository
public class UserRoleDaoImpl extends AbstractDao implements UserRoleDao {

	private UserRole userRole;

	@Override
	public UserRole findUserRoleById(int userRoleId) {
		Criteria criteria = getSession().createCriteria(UserRole.class);
		criteria.add(Restrictions.eq("id", userRoleId));
		
		userRole = (UserRole)criteria.uniqueResult();
		return userRole;
	}

}
