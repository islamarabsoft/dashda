/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dashda.data.entities.User;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ProductBySpecialtyInputDTO;
import com.dashda.controllers.dto.SpecialtyOutputDto;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ProductSpecialty;
import com.dashda.data.entities.Specialty;
import com.dashda.data.repositories.ProductDao;
import com.dashda.data.repositories.SpecialtyDao;
import com.dashda.data.repositories.UserDao;

/**
 * @author mhanafy
 *
 */
@Service
public class ProductServiceImpl extends ServicesManager implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	SpecialtyDao specialtyDao; 
	
	@Autowired
	UserDao userDao;
	
	@Override
	public AppResponse getProductBySpecialty(String username,
			ProductBySpecialtyInputDTO productBySpecialtyInputDTO) throws ProductServiceException {
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		
		if(employee == null)
			throw new ProductServiceException(ERROR_CODE_1001);
				
		Specialty specialty = specialtyDao
				.findSpecialty(productBySpecialtyInputDTO.getSpecialtyId());
		
		if(specialty == null)
			throw new ProductServiceException(ERROR_CODE_1020);
		
		List<ProductSpecialty> productSpecialties = productDao
				.findProductBySpecialty(specialty, employee.getAccount());
		
		List specialties = new ArrayList<SpecialtyOutputDto>();
		
		for (Iterator iterator = productSpecialties.iterator(); iterator.hasNext();) {
			ProductSpecialty productSpecialty = (ProductSpecialty) iterator.next();
			
			SpecialtyOutputDto specialtyOutputDto = new SpecialtyOutputDto();
			
			specialtyOutputDto.setId(productSpecialty.getProduct().getId());
			specialtyOutputDto.setName(productSpecialty.getProduct().getName());
			
			specialties.add(specialtyOutputDto);
		}
		
		return okListResponse(specialties, "List Size is : " + specialties.size());
	}

}
