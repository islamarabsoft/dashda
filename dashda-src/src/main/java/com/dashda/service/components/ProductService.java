/**
 * 
 */
package com.dashda.service.components;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.product.AssignProductSpecialtyInputDTO;
import com.dashda.controllers.dto.product.ProductBySpecialtyInputDTO;
import com.dashda.controllers.dto.product.ProductCreateInputDTO;
import com.dashda.controllers.dto.product.ProductDeleteInputDTO;
import com.dashda.controllers.dto.product.ProductUpdateInputDTO;

/**
 * @author mhanafy
 *
 */
public interface ProductService {

	public AppResponse getProductBySpecialty(String username
			, ProductBySpecialtyInputDTO productBySpecialtyInputDTO)
				throws ProductServiceException;

	public AppResponse createProduct(String username
			, ProductCreateInputDTO productCreateInputDTO)
				throws ProductServiceException;

	public AppResponse udapteProduct(String username
			, ProductUpdateInputDTO productUpdateInputDTO)
					throws ProductServiceException;

	public AppResponse deleteProduct(String username
			, ProductDeleteInputDTO productDeleteInputDTO)
					throws ProductServiceException;

	public AppResponse listOfProducts(String username)
			throws ProductServiceException;

	public AppResponse assignProductSpecialty(String username
			, AssignProductSpecialtyInputDTO assignProductSpecialtyDTO)
					throws ProductServiceException;

	public AppResponse getAllSpecialties() throws ProductServiceException;

	public AppResponse getAccountProductLines(String username) throws ProductServiceException;

}
