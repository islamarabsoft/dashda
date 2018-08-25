/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.dashda.controllers.dto.report.ReportVisitsPerProductDetailsInputDTO;
import com.dashda.controllers.dto.report.ReportVisitsPerProductInputDTO;

/**
 * @author mohamed.hanfy
 *
 */
public interface ReportVisitsPerProductService {

	public List visitsPerProduct(String userName
			, ReportVisitsPerProductInputDTO reportVisitsPerProductInputDTO) 
					throws ReportVisitsPerProductServiceExcepion, ParseException;

	public List visitsPerProductDetails(String username
			, @Valid ReportVisitsPerProductDetailsInputDTO reportVisitsPerProductDetailsInputDTO)
					throws ReportVisitsPerProductServiceExcepion, ParseException;
}
