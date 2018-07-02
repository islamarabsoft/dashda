/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AppResponse;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportLookupService {

	AppResponse findSubordinates(String username)throws ReportLookupServiceException;

	AppResponse findSubordinatesWithoutLowLevel(String username)throws ReportLookupServiceException;

	AppResponse findServiceProviderDistrict(String username)throws ReportLookupServiceException;

	AppResponse findServiceProviderSpecialty(String username)throws ReportLookupServiceException;

	AppResponse findServiceProvider(String username)throws ReportLookupServiceException;

	AppResponse findSubordinatesLowLevel(String username)throws ReportLookupServiceException;

}
