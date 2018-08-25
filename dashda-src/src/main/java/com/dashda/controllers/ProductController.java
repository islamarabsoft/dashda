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

import com.dashda.controllers.dto.product.AssignProductSpecialtyInputDTO;
import com.dashda.controllers.dto.product.ProductBySpecialtyInputDTO;
import com.dashda.controllers.dto.product.ProductCreateInputDTO;
import com.dashda.controllers.dto.product.ProductDeleteInputDTO;
import com.dashda.controllers.dto.product.ProductUpdateInputDTO;
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/create-product")
	@Secured("ROLE_PRODUCT_CONTRIBUTOR")
	public ResponseEntity createProduct(@AuthenticationPrincipal User user
			,@Valid @RequestBody ProductCreateInputDTO productCreateInputDTO)
					throws ProductServiceException {
	
		return returnResponseEntityCreated(productService.createProduct(user.getUsername()
				, productCreateInputDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/update-product")
	@Secured("ROLE_PRODUCT_CONTRIBUTOR")
	public ResponseEntity updateProduct(@AuthenticationPrincipal User user
			,@Valid @RequestBody ProductUpdateInputDTO productUpdateInputDTO)
					throws ProductServiceException {
	
		return returnResponseEntityOk(productService.udapteProduct(user.getUsername()
				, productUpdateInputDTO));
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/delete-product")
	@Secured("ROLE_PRODUCT_CONTRIBUTOR")
	public ResponseEntity deleteProduct(@AuthenticationPrincipal User user
			,@Valid @RequestBody ProductDeleteInputDTO productDeleteInputDTO)
					throws ProductServiceException {
	
		return returnResponseEntityOk(productService.deleteProduct(user.getUsername()
				, productDeleteInputDTO));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/products")
	@Secured("ROLE_PRODUCT_CONTRIBUTOR")
	public ResponseEntity listOfProducts(@AuthenticationPrincipal User user)
					throws ProductServiceException {
	
		return returnResponseEntityOk(productService.listOfProducts(user.getUsername()));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/assign-product-specialty")
	@Secured("ROLE_PRODUCT_CONTRIBUTOR")
	public ResponseEntity assignProductSpecialty(@AuthenticationPrincipal User user
			, @Valid @RequestBody AssignProductSpecialtyInputDTO assignProductSpecialtyDTO)
					throws ProductServiceException {
	
		return returnResponseEntityOk(productService.assignProductSpecialty(user.getUsername(), assignProductSpecialtyDTO));
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/specialties")
	@Secured("ROLE_PRODUCT_CONTRIBUTOR")
	public ResponseEntity getAllSpecialties(@AuthenticationPrincipal User user)
					throws ProductServiceException {
	
		return returnResponseEntityOk(productService.getAllSpecialties());
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/product-lines")
	@Secured("ROLE_PRODUCT_CONTRIBUTOR")
	public ResponseEntity getAccountProductLines(@AuthenticationPrincipal User user)
					throws ProductServiceException {
	
		return returnResponseEntityOk(productService.getAccountProductLines(user.getUsername()));
	}
}
