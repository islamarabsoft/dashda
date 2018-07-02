/**
 * 
 */
package com.dashda.service.components;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class DistrictOutputDTO implements AbstractDTO {
	
	private int id;
	private String name;
	
	
	public DistrictOutputDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DistrictOutputDTO(int id, String name) {
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
