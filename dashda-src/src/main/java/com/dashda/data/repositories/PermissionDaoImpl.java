/**
 * 
 */
package com.dashda.data.repositories;

import org.springframework.stereotype.Repository;

import com.dashda.data.entities.Permission;

/**
 * @author mhanafy
 *
 */
@Repository
public class PermissionDaoImpl extends AbstractDao implements PermissionDao {

	@Override
	public void addPermission(Permission permission) {
		save(permission);
	}

}
