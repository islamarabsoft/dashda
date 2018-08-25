/**
 * 
 */
package com.dashda.controllers.dto.visit;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

/**
 * @author mhanafy
 *
 */
public class VisitCompleteInputDTO {

	@Digits(fraction=0, integer=8)
	private int id;
	private List<Integer> productIds;
	private int doubleVisit;
	private List<Integer> managerIds;
	@NotEmpty
	private String comment;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
	public int getDoubleVisit() {
		return doubleVisit;
	}
	public void setDoubleVisit(int doubleVisit) {
		this.doubleVisit = doubleVisit;
	}
	public List<Integer> getManagerIds() {
		return managerIds;
	}
	public void setManagerIds(List<Integer> managerIds) {
		this.managerIds = managerIds;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
}
