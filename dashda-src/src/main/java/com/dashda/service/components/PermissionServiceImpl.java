/**
 * 
 */
package com.dashda.service.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.data.entities.Permission;
import com.dashda.data.entities.PermissionType;
import com.dashda.data.entities.UserRole;
import com.dashda.data.entities.UserRolePermission;
import com.dashda.data.repositories.PermissionDao;
import com.dashda.data.repositories.user.UserRoleDao;
import com.dashda.data.repositories.user.UserRolePermissionDao;
import com.dashda.enums.PermissionTypeEnum;
import com.dashda.exception.PermissionServiceExceptioManager;

/**
 * @author mhanafy
 *
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServicesManager implements PermissionService{

	@Autowired
	PermissionDao permissionDao;
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Autowired
	UserRolePermissionDao userRolePermissionDao;

	private UserRole userRole;
	
	private UserRolePermission userRolePermission;
	
	@Override
	public void createTemplateAndAssignToUserGroup(String permissionName, int assignedToUserRoleId)
			throws PermissionServiceExceptioManager {
		
		userRole = userRoleDao.findUserRoleById(assignedToUserRoleId);
		
		if(userRole == null)
			throw new PermissionServiceExceptioManager(ERROR_CODE_1014);
		
		Permission permission = new Permission(permissionName, permissionName, 
				new PermissionType(PermissionTypeEnum.CLIENT_TEMPLATE.getValue()));
		
		permissionDao.addPermission(permission);
		
		userRolePermission = new UserRolePermission(permission, userRole);
		
		userRolePermissionDao.addUserRolePermission(userRolePermission);
	}

}
