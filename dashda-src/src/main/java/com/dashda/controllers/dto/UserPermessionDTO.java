/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.NotNull;

/**
 * @author mhanafy
 *
 */
public class UserPermessionDTO {

	@NotNull
	private int id;
	
	@NotNull
	private String permession;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermession() {
		return permession;
	}

	public void setPermession(String permession) {
		this.permession = permession;
	}
	
	
}
