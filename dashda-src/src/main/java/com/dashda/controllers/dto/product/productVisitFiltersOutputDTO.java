package com.dashda.controllers.dto.product;

public class productVisitFiltersOutputDTO {

	private int productId;
	private String productName;

	private int specialId;
	private String specialName;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getSpecialId() {
		return specialId;
	}
	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}
	public String getSpecialName() {
		return specialName;
	}
	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}
	public productVisitFiltersOutputDTO(int productId, String productName
			, int specialId, String specialName) {
		super();
		this.productId = productId;
		this.productName = productName;
	
		this.specialId = specialId;
		this.specialName = specialName;
	}
	
}
