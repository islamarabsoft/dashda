/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;

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
