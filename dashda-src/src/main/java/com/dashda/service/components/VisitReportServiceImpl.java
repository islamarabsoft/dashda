package com.dashda.service.components;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.SpecialtyOutputDto;
import com.dashda.controllers.dto.employee.EmployeeOutputDTO;
import com.dashda.controllers.dto.product.ProducLineOutputDTO;
import com.dashda.controllers.dto.product.ProductOutputDTO;
import com.dashda.controllers.dto.product.productVisitFiltersOutputDTO;
import com.dashda.controllers.dto.visit.VisitReportCountOutputDTO;
import com.dashda.controllers.dto.visit.VisitReportInputDTO;
import com.dashda.data.entities.EmployeeVisitFilter;
import com.dashda.data.entities.Product;
import com.dashda.data.entities.ProductLine;
import com.dashda.data.entities.ProductVisitFilters;
import com.dashda.data.entities.Specialty;
import com.dashda.data.entities.VisitReportCount;
import com.dashda.data.repositories.SpecialtyDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.data.repositories.employee.EmployeeDao;
import com.dashda.data.repositories.product.ProductDao;
import com.dashda.exception.VisitReportException;
import com.dashda.utilities.DateUtilities;

@Service
public class VisitReportServiceImpl extends ServicesManager implements VisitReportService {

	@Autowired
	EmployeeDao employeedao;
	@Autowired
	SpecialtyDao specialtydao;
	
	@Autowired
	VisitDao visitdao;
	@Autowired
	ProductDao productdao;
	
	@Override
	public Object getVisitEmployeesFilters() throws VisitReportException, ParseException {
		
		
		List<EmployeeOutputDTO> employeesOutputDTO = new ArrayList<>();
		

	////////////e	
	List<EmployeeVisitFilter> employeesfilter=	employeedao.findEmployeeBytypes();
	for (Iterator iterator = employeesfilter.iterator(); iterator.hasNext();) {
		EmployeeVisitFilter employeefilter = (EmployeeVisitFilter) iterator.next();
		EmployeeOutputDTO employeeOutputDTO = new EmployeeOutputDTO();			 
		employeeOutputDTO.setId(employeefilter.getEmployeeId());
		employeeOutputDTO.setManagerId(employeefilter.getManagerId());
		employeeOutputDTO.setName(employeefilter.getEmployeename());
		employeeOutputDTO.setType(employeefilter.getType());
		
		employeesOutputDTO.add(employeeOutputDTO);
	}
	
	
	
	
	
		return employeesOutputDTO;
	}

	@Override
	public Object getVisitProductFilter() throws VisitReportException, ParseException {
		// TODO Auto-generated method stub
		
		List<Product> products=productdao.findall(Product.class);
		
		List<ProductOutputDTO> productsOutputDto=new ArrayList<>();
		for (Iterator iterator = products.iterator(); iterator.hasNext();)
		{
			
			Product product=(Product) iterator.next();
			ProductOutputDTO productvisitfilter=new ProductOutputDTO(product.getId(),product.getName(),product.getProductLine().getId());
					
			
			//mapper.map(special, productSpecialty.getSpecialty());
			
			productsOutputDto.add(productvisitfilter);
			
		}
		
		return productsOutputDto;
	}

	@Override
	public Object getVisitProductLineFilter() throws VisitReportException, ParseException {
		// TODO Auto-generated method stub
		
		
		
		
		List<ProductLine> products=productdao.findall(ProductLine.class);
		
		List<ProducLineOutputDTO> productsOutputDto=new ArrayList<>();
		for (Iterator iterator = products.iterator(); iterator.hasNext();)
		{
			
			ProductLine product=(ProductLine) iterator.next();
			ProducLineOutputDTO productvisitfilter=new ProducLineOutputDTO(product.getId(),product.getName());
					
			
			//mapper.map(special, productSpecialty.getSpecialty());
			
			productsOutputDto.add(productvisitfilter);
			
		}
		
		return productsOutputDto;
	}
	
	@Override
	public Object getVisitSpecialtyFilter() throws VisitReportException, ParseException {
		// TODO Auto-generated method stub
		
		List<ProductVisitFilters> specialtys=productdao.findAllProductSpecialty();
		
		List<productVisitFiltersOutputDTO> specialtysOutputDto=new ArrayList<>();
		for (Iterator iterator = specialtys.iterator(); iterator.hasNext();)
		{
			
			ProductVisitFilters specialty=(ProductVisitFilters) iterator.next();
			productVisitFiltersOutputDTO specialtyOutput=new productVisitFiltersOutputDTO(specialty.getProductId(),specialty.getProductName(),specialty.getSpecialId(),specialty.getSpecialName());
					
			
			//mapper.map(special, productSpecialty.getSpecialty());
			
			specialtysOutputDto.add(specialtyOutput);
			
		}
		
		return specialtysOutputDto;
	}
	
	
	@Override
	public Object getVisitsReportCount(VisitReportInputDTO visitReportInputDTO) throws VisitReportException, ParseException {
		// TODO Auto-generated method stub
		
		/*Date dateFrom = DateUtilities.convertToDate(visitReportInputDTO.getDatefrom(), 
				DateUtilities.DATE_FORMATE_PATTERN);
		Date dateTo = DateUtilities.convertToDate(visitReportInputDTO.getDateto(), 
				DateUtilities.DATE_FORMATE_PATTERN);*/
		List<VisitReportCount> data=visitdao.findVisitReportCount(visitReportInputDTO);
		
		List<VisitReportCountOutputDTO> output=new ArrayList<>();
		for (Iterator iterator = data.iterator(); iterator.hasNext();)
		{
			
			VisitReportCount tempdata=(VisitReportCount) iterator.next();
			VisitReportCountOutputDTO visitOutput=new VisitReportCountOutputDTO(tempdata.getProductlineid(),tempdata.getProductline()
			,tempdata.getRegional(),tempdata.getRegionalid(),tempdata.getFlm(),tempdata.getFlmid(),tempdata.getMp(),tempdata.getMpid(),tempdata.getCount()		);
					
			
			//mapper.map(special, productSpecialty.getSpecialty());
			
			output.add(visitOutput);
			
		}
		
		return output;
	}
	
}
