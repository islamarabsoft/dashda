/**
 * 
 */
package com.dashda.controllers.dto.product;

import javax.validation.constraints.Digits;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class ProductBySpecialtyInputDTO implements AbstractDTO {

	
	@Digits(fraction = 0, integer = 8)
	private int specialtyId;

	
	public int getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}
	
	
}
