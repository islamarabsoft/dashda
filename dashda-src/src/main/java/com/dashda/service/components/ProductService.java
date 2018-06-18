/**
 * 
 */
package com.dashda.service.components;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ProductBySpecialtyInputDTO;
import com.dashda.controllers.dto.ProductCreateInputDTO;
import com.dashda.controllers.dto.ProductDeleteInputDTO;
import com.dashda.controllers.dto.ProductUpdateInputDTO;

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

}
