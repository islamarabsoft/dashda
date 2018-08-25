/**
 * 
 */
package com.dashda.controllers.dto.product;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class ProductOutputDTO implements AbstractDTO {

	@Digits(fraction = 0, integer = 8)
	private int id;
	@NotEmpty
	private String name;
	private int lineId;
	
	
	
	public ProductOutputDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductOutputDTO(int id, String name, int lineId) {
		this.id = id;
		this.name = name;
		this.lineId = lineId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
