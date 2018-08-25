/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.dashda.controllers.dto.report.ReportVisitsPerSpecialtyDetailsInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerSpecialtyInputDTO;
import com.dashda.exception.ReportVisitsPerSpecialtyServiceExcepion;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportVisitsPerSpecialtyService {

	List visitsPerSpecialty(String username,
			@Valid ReportVisitsPerSpecialtyInputDTO reportVisitsPerSpecialtyInputDTO)
					throws ReportVisitsPerSpecialtyServiceExcepion, ParseException;

	List visitsPerSpecialtyDetails(String username,
			@Valid ReportVisitsPerSpecialtyDetailsInputDTO reportVisitsPerSpecialtyDetailsInputDTO)
					throws ReportVisitsPerSpecialtyServiceExcepion, ParseException;

}
