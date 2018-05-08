package com.dashda.enums;

public enum ServiceProviderTypeEnum {
	DOCTOR(1), PHARMACY(2), HOSPITAL(3);
	
	private int value;
	
	ServiceProviderTypeEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
