/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.InputRequestApprovalDTO;
import com.dashda.controllers.dto.InputRequestRejectDTO;
import com.dashda.exception.ApprovalServiceException;
import com.dashda.exception.ScheduleExceptionManager;

/**
 * @author mhanafy
 *
 */
public interface RequestService {

	public AppResponse getListOfPendingApprovalrequests(String username) throws ApprovalServiceException;

	public AppResponse approveRequest(String username, 
			InputRequestApprovalDTO inputRequestApprovalDTO) 
					throws ApprovalServiceException;

	public AppResponse rejectRequest(String username, 
			InputRequestRejectDTO inputRequestRejectDTO)throws ApprovalServiceException;

}
