/**
 * 
 */
package com.dashda.service.components;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ProductBySpecialtyInputDTO;

/**
 * @author mhanafy
 *
 */
public interface ProductService {

	public AppResponse getProductBySpecialty(String username
			, ProductBySpecialtyInputDTO productBySpecialtyInputDTO)
				throws ProductServiceException;

}
