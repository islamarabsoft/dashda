package com.dashda.controllers.dto.visit;

import java.util.List;

import com.dashda.controllers.dto.SpecialtyOutputDto;
import com.dashda.controllers.dto.employee.EmployeeOutputDTO;
import com.dashda.controllers.dto.product.ProducLineOutputDTO;
import com.dashda.controllers.dto.product.ProductOutputDTO;
import com.dashda.controllers.dto.product.productVisitFiltersOutputDTO;

public class VisitReportFiltersDTO {

	
	private List<EmployeeOutputDTO> empolyees;
	private List<productVisitFiltersOutputDTO> productvisitfilter;
	
	
	public List<productVisitFiltersOutputDTO> getProductvisitfilter() {
		return productvisitfilter;
	}



	public void setProductvisitfilter(List<productVisitFiltersOutputDTO> productvisitfilter) {
		this.productvisitfilter = productvisitfilter;
	}



	public VisitReportFiltersDTO()
	{
		
	}
	
	
	public List<EmployeeOutputDTO> getEmpolyees() {
		return empolyees;
	}
	public void setEmpolyees(List<EmployeeOutputDTO> empolyees) {
		this.empolyees = empolyees;
	}

	
}
