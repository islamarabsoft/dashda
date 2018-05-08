/**
 * 
 */
package com.dashda.enums;

/**
 * @author mhanafy
 *
 */
public enum VisitStatusEnum {
	PLANNED(1), COMPLETE (2), CANCELED(3);

	private int value;
	
	VisitStatusEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
