/**
 * 
 */
package com.dashda.controllers.dto.product;

/**
 * @author mhanafy
 *
 */
public class ProductUpdateInputDTO {

	private int id;
	private String name;
	private int lineId;
	
	
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
