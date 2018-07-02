/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.exception.ReportCoverageServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportCoverageService {

	AppResponse userCoverage(String username) throws ReportCoverageServiceException;

}
