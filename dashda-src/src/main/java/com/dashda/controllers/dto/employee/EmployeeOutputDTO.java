/**
 * 
 */
package com.dashda.controllers.dto.employee;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class EmployeeOutputDTO implements AbstractDTO {

	private int Id;
	private String name;
	private int managerId;
	private int type;
	
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	
}
