/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author mhanafy
 *
 */
public class UserDTO {

	@NotNull
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String username;
	
	@NotNull
	private List<UserPermessionDTO> userPermessionDTOs;
	
	private String employeeJobTitle;
	
	private String employeeNumber;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserPermessionDTO> getUserPermessionDTOs() {
		return userPermessionDTOs;
	}

	public void setUserPermessionDTOs(List<UserPermessionDTO> userPermessionDTOs) {
		this.userPermessionDTOs = userPermessionDTOs;
	}

	public String getEmployeeJobTitle() {
		return employeeJobTitle;
	}

	public void setEmployeeJobTitle(String employeeJobTitle) {
		this.employeeJobTitle = employeeJobTitle;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	
}
