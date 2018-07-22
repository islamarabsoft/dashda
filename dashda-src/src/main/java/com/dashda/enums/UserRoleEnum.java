/**
 * 
 */
package com.dashda.enums;

/**
 * @author mohamed.hanfy
 *
 */
public enum UserRoleEnum {

	ADMIN(1), MEDICAL_REP(2), DISTRICT_MANAGER(3), REGIONAL_MANAGER(4), SALES_MANAGER(5), CHAIRMAN_EXECUTIVE_OFFICER(6), PRODUCT_MANAGER(7);
	
	private int value;
	
	UserRoleEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
