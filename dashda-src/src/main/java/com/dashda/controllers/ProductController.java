/**
 * 
 */
package com.dashda.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashda.controllers.dto.ProductBySpecialtyInputDTO;
import com.dashda.service.components.ProductService;
import com.dashda.service.components.ProductServiceException;

/**
 * @author mhanafy
 *
 */
@RestController
public class ProductController extends AbstractController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/product-by-specialty")
	@Secured("ROLE_VISIT_CONTRIBUTOR")
	public ResponseEntity productBySpecialty(@AuthenticationPrincipal User user
				,@Valid @RequestBody ProductBySpecialtyInputDTO productBySpecialtyInputDTO) 
						throws ProductServiceException {
		
		return returnResponseEntityOk(productService
				.getProductBySpecialty(user.getUsername(), productBySpecialtyInputDTO));
	}
}
