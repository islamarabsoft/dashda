/**
 * 
 */
package com.dashda.controllers.dto.product;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class ProducLineOutputDTO implements AbstractDTO {

	private int id;
	private String name;
	
	
	public ProducLineOutputDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProducLineOutputDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
	
}
