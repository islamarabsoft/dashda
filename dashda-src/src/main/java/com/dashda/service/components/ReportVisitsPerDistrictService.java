/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.dashda.controllers.dto.report.ReportVisitsPerDistrictDetailsInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerDistrictInputDTO;
import com.dashda.exception.ReportVisitsPerDistrictServiceExcepion;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportVisitsPerDistrictService {

	List visitsPerDistrict(String username, 
			@Valid ReportVisitsPerDistrictInputDTO reportVisitsPerDistrictInputDTO)
				throws ReportVisitsPerDistrictServiceExcepion, ParseException;

	List visitsPerDistrictDetails(String username,
			@Valid ReportVisitsPerDistrictDetailsInputDTO reportVisitsPerDistrictDetailsInputDTO)
				throws ReportVisitsPerDistrictServiceExcepion, ParseException;

}
