/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.PortInUseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dashda.data.entities.User;
import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ProductBySpecialtyInputDTO;
import com.dashda.controllers.dto.ProductCreateInputDTO;
import com.dashda.controllers.dto.ProductDeleteInputDTO;
import com.dashda.controllers.dto.ProductOutputDTO;
import com.dashda.controllers.dto.ProductUpdateInputDTO;
import com.dashda.controllers.dto.SpecialtyOutputDto;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Product;
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

	@Override
	public AppResponse createProduct(String username, ProductCreateInputDTO productCreateInputDTO)
			throws ProductServiceException {
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new ProductServiceException(ERROR_CODE_1001);
		}
		
		Product product = new Product();
		product.setName(productCreateInputDTO.getName());
		product.setAccount(employee.getAccount());
		
		productDao.saveProduct(product);
		
		ProductOutputDTO productCreateOutputDTO = 
				new ProductOutputDTO(product.getId(), product.getName());
		return createResponse(productCreateOutputDTO, "Product Created Successfully");
	}

	@Override
	public AppResponse udapteProduct(String username, ProductUpdateInputDTO productUpdateInputDTO)
			throws ProductServiceException {
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new ProductServiceException(ERROR_CODE_1001);
		}
		
		Product product = productDao
				.findProductByIdAndAccount(productUpdateInputDTO.getId(), employee.getAccount());
		if (product == null) 
			throw new ProductServiceException(ERROR_CODE_1020);
		product.setName(productUpdateInputDTO.getName());
		
		productDao.saveProduct(product);
		
		ProductOutputDTO outputDTO = 
				new ProductOutputDTO(product.getId(), product.getName());
		return okResponse(outputDTO, "Product Updated Successfully");
	}

	@Override
	public AppResponse deleteProduct(String username, ProductDeleteInputDTO productDeleteInputDTO)
			throws ProductServiceException {
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new ProductServiceException(ERROR_CODE_1001);
		}
		
		Product product = productDao
				.findProductByIdAndAccount(productDeleteInputDTO.getId(), employee.getAccount());
		if (product == null) 
			throw new ProductServiceException(ERROR_CODE_1020);
		
		List<ProductSpecialty> productSpecialties = 
				productDao.findProductBySpecialty(product, employee.getAccount());
		if(!productSpecialties.isEmpty())
			throw new ProductServiceException(ERROR_CODE_1027);
		
		productDao.deleteProduct(product);
		
		return emptyResponse("Product Deleted Successfully");
	}

	@Override
	public AppResponse listOfProducts(String username) throws ProductServiceException {
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new ProductServiceException(ERROR_CODE_1001);
		}
		
		List<Product> products = productDao.finAllProductsByAccount(employee.getAccount());
		
		List productOutputDTOs = new ArrayList<ProductOutputDTO>();
		
		for (Iterator productIt = products.iterator(); productIt.hasNext();) {
			Product product = (Product) productIt.next();
			ProductOutputDTO productDto = new ProductOutputDTO(
							product.getId(), product.getName());
			
			productOutputDTOs.add(productDto);
		}

		return okListResponse(productOutputDTOs, "List Size is : " + productOutputDTOs.size());
	}

	
	
}
