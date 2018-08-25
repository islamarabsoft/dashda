/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.dashda.controllers.dto.report.ReportVisitsPerFLMDetailsInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerFLMInputDTO;
import com.dashda.exception.ReportVisitsPerFLMServiceExcepion;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportVisitsPerFLMService {

	List visitsPerFLM(String username, 
			@Valid ReportVisitsPerFLMInputDTO reportVisitsPerFLMInputDTO) 
					throws ReportVisitsPerFLMServiceExcepion, ParseException;

	List visitsPerFLMDetails(String username,
			@Valid ReportVisitsPerFLMDetailsInputDTO reportVisitsPerFLMDetailsInputDTO)
					throws ReportVisitsPerFLMServiceExcepion, ParseException;

}
