/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class SpecialtyOutputDto implements AbstractDTO {

	private int Id;
	private String name;
	
	
	
	
	public SpecialtyOutputDto(int id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	public SpecialtyOutputDto() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
}
