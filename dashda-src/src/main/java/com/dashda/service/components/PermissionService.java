/**
 * 
 */
package com.dashda.service.components;

import com.dashda.exception.PermissionServiceExceptioManager;

/**
 * @author mhanafy
 *
 */
public interface PermissionService {

	public void createTemplateAndAssignToUserGroup(String permission, int assignedToUserRoleId) throws PermissionServiceExceptioManager;

}
