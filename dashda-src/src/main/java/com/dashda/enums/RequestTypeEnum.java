package com.dashda.enums;

public enum RequestTypeEnum {

	SCHEDULE(1), OFF_VISIT(2);
	
	
	private int value;

	private RequestTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
