/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.data.entities.User;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.SpecialtyOutputDto;
import com.dashda.controllers.dto.product.AssignProductSpecialtyInputDTO;
import com.dashda.controllers.dto.product.AssignProductSpecialtyOutputDTO;
import com.dashda.controllers.dto.product.ProducLineOutputDTO;
import com.dashda.controllers.dto.product.ProductBySpecialtyInputDTO;
import com.dashda.controllers.dto.product.ProductCreateInputDTO;
import com.dashda.controllers.dto.product.ProductDeleteInputDTO;
import com.dashda.controllers.dto.product.ProductOutputDTO;
import com.dashda.controllers.dto.product.ProductUpdateInputDTO;
import com.dashda.data.entities.Account;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Product;
import com.dashda.data.entities.ProductLine;
import com.dashda.data.entities.ProductSpecialty;
import com.dashda.data.entities.Specialty;
import com.dashda.data.repositories.SpecialtyDao;
import com.dashda.data.repositories.product.ProductDao;
import com.dashda.data.repositories.user.UserDao;

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
		Account account = employee.getAccount();
		ProductLine productLine = employee.getProductLine();
		
		Specialty specialty = specialtyDao
				.findSpecialty(productBySpecialtyInputDTO.getSpecialtyId());
		
		if(specialty == null)
			throw new ProductServiceException(ERROR_CODE_1020);
		
		List<ProductSpecialty> productSpecialties = null;
		if(productLine == null)
			productSpecialties = productDao
				.findProductBySpecialty(specialty, account);
		else
			productSpecialties = productDao
				.findProductBySpecialty(specialty, account, productLine);
		
		List products = new ArrayList<ProductOutputDTO>();
		
		for (Iterator iterator = productSpecialties.iterator(); iterator.hasNext();) {
			ProductSpecialty productSpecialty = (ProductSpecialty) iterator.next();
			
			ProductOutputDTO productOutputDTO = new ProductOutputDTO(productSpecialty.getProduct().getId()
					, productSpecialty.getProduct().getName(), productSpecialty.getProduct().getProductLine().getId());
			
			products.add(productOutputDTO);
		}
		
		return okListResponse(products, "List Size is : " + products.size());
	}

	@Override
	public AppResponse createProduct(String username, ProductCreateInputDTO productCreateInputDTO)
			throws ProductServiceException {
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new ProductServiceException(ERROR_CODE_1001);
		}
		Account account = employee.getAccount();
		ProductLine productLine = productDao.findProductLine(productCreateInputDTO.getLineId(), account);
		
		if(productLine == null)
			throw new ProductServiceException(ERROR_CODE_1020);
		
		Product product = new Product();
		product.setName(productCreateInputDTO.getName());
		product.setAccount(employee.getAccount());
		product.setProductLine(productLine);
		
		productDao.saveProduct(product);
		
		ProductOutputDTO productCreateOutputDTO = 
				new ProductOutputDTO(product.getId(), product.getName(), product.getProductLine().getId());
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
		
		Account account = employee.getAccount();
		
		Product product = productDao
				.findProductByIdAndAccount(productUpdateInputDTO.getId(), employee.getAccount());
		
		ProductLine productLine = productDao.findProductLine(productUpdateInputDTO.getLineId(), account);
		if(productLine == null)
			throw new ProductServiceException(ERROR_CODE_1020);
		
		if (product == null) 
			throw new ProductServiceException(ERROR_CODE_1020);
		product.setName(productUpdateInputDTO.getName());
		product.setProductLine(productLine);
		
		productDao.saveProduct(product);
		
		ProductOutputDTO outputDTO = 
				new ProductOutputDTO(product.getId(), product.getName(), product.getProductLine().getId());
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
							product.getId(), product.getName(), product.getProductLine().getId());
			
			productOutputDTOs.add(productDto);
		}

		return okListResponse(productOutputDTOs, "List Size is : " + productOutputDTOs.size());
	}

	@Override
	public AppResponse assignProductSpecialty(String username, AssignProductSpecialtyInputDTO assignProductSpecialtyDTO)
			throws ProductServiceException {
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if (employee == null) 
			throw new ProductServiceException(ERROR_CODE_1001);
		Account account = employee.getAccount();
		
		AssignProductSpecialtyOutputDTO assignProductSpecialtyOutputDTO = new AssignProductSpecialtyOutputDTO();
		
		Specialty specialty = specialtyDao.findSpecialty(assignProductSpecialtyDTO.getSpecialtyId());
		if (specialty == null) 
			throw new ProductServiceException(ERROR_CODE_1020);
		
		productDao.deleteProductSpecialty(specialty);
		List<Integer> assignIds = new ArrayList<Integer>();
		for (Iterator productIt = assignProductSpecialtyDTO.getProductIds().iterator()
				; productIt.hasNext();) {
			Integer productId = (Integer)productIt.next();
			Product product = productDao.findProductByIdAndAccount(productId, account);
			ProductSpecialty productSpecialty = new ProductSpecialty(product, specialty);
			
			productDao.saveProductSpecialty(productSpecialty);
			assignIds.add(productSpecialty.getId());
		}
		
		assignProductSpecialtyOutputDTO.setAssignIds(assignIds);
		return okResponse(assignProductSpecialtyOutputDTO, "Request Handled Successfully");
	}

	@Override
	public AppResponse getAllSpecialties() throws ProductServiceException {
		List<Specialty> specialties = specialtyDao.findAll();
		List specialtyOutputDtos = new ArrayList<>();
		
		for (Iterator specialtyIT = specialties.iterator(); specialtyIT.hasNext();) {
			
			Specialty specialty = (Specialty) specialtyIT.next();
			SpecialtyOutputDto specialtyOutputDto = new SpecialtyOutputDto(specialty.getId(), specialty.getName());
			specialtyOutputDtos.add(specialtyOutputDto);
		}
		
		return okListResponse(specialtyOutputDtos);
	}

	@Override
	public AppResponse getAccountProductLines(String username) throws ProductServiceException {
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new ProductServiceException(ERROR_CODE_1001);
		}
		Account account = employee.getAccount();
		List producLineOutputDTOs = new ArrayList();
		
		List<ProductLine> productLines = productDao.finPrductLines(account);
		
		for (Iterator productLineIt = productLines.iterator(); productLineIt.hasNext();) {
			ProductLine productLine = (ProductLine) productLineIt.next();
			
			ProducLineOutputDTO producLineOutputDTO = 
					new ProducLineOutputDTO(productLine.getId(), productLine.getName());
			
			producLineOutputDTOs.add(producLineOutputDTO);
		}
		
		
		return okListResponse(producLineOutputDTOs);
	}

	
	
}
