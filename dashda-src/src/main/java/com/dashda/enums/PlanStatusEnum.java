package com.dashda.enums;

public enum PlanStatusEnum {

	DRAFT(1), SENT_FOR_APPROVAL(2), APPROVED(3), REJECTED(4);
	
	
	private int value;

	private PlanStatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
