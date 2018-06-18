/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class ProductCreateInputDTO implements AbstractDTO {

	@NotEmpty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
