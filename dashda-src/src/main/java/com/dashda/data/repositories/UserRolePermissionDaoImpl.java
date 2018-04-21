/**
 * 
 */
package com.dashda.data.repositories;

import org.springframework.stereotype.Repository;

import com.dashda.data.entities.UserRolePermission;

/**
 * @author mhanafy
 *
 */
@Repository
public class UserRolePermissionDaoImpl extends AbstractDao implements UserRolePermissionDao {

	/* (non-Javadoc)
	 * @see com.dashda.data.repositories.UserRolePermissionDao#addUserRolePermission(com.dashda.data.entities.UserRolePermission)
	 */
	@Override
	public void addUserRolePermission(UserRolePermission userRolePermission) {
		save(userRolePermission);

	}

}
