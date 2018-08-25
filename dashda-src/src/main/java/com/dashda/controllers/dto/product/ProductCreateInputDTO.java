/**
 * 
 */
package com.dashda.controllers.dto.product;

import javax.validation.constraints.NotEmpty;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class ProductCreateInputDTO implements AbstractDTO {

	@NotEmpty
	private String name;
	private int lineId;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	
	
	
}
