/**
 * 
 */
package com.dashda.enums;

/**
 * @author mhanafy
 *
 */
public enum ScheduleStatusEnum {
	PENDING_APPROVAL(1), APPROVED(2), REJECTED(3);

	private int value;
	
	ScheduleStatusEnum(int value) {
		this.value = value;
		// TODO Auto-generated constructor stub
	}
	
	public int getValue() {
		return this.value;
	}
	
	
}
