/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.exception.ApprovalServiceException;

/**
 * @author mhanafy
 *
 */
public interface ApprovalService {

	public AppResponse getListOfPendingApprovalrequests(String username) throws ApprovalServiceException;

}
