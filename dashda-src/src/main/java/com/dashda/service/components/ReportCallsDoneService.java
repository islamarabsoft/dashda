/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.exception.ReportCallsDoneServiceException;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportCallsDoneService {

	AppResponse userCallsDone(String username) throws ReportCallsDoneServiceException;
}
