/**
 * 
 */
package com.dashda.enums;

/**
 * @author mhanafy
 *
 */
public enum CalendarActivityStatusEnum {

	DRAFT(1), PLANNED(2), VISITED(3), DISCARD(4), OFF_VISIT(5);
	
	private int value;
	
	CalendarActivityStatusEnum(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
