package com.dashda.enums;

public enum OffVisitStatusEnum {
	
	PENDING_APPROVAL(1), ACCEPTED(2), REJECTED(3);
	
	private int value;
	
	OffVisitStatusEnum(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
