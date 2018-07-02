/**
 * 
 */
package com.dashda.service.components;

import org.springframework.http.ResponseEntity;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ReportTargetVisitInputDTO;
import com.dashda.exception.ReportVisitServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportVisitService {

	AppResponse getTargetVisits(String username, ReportTargetVisitInputDTO reportTargetVisitInputDTO) throws ReportVisitServiceException;

}
