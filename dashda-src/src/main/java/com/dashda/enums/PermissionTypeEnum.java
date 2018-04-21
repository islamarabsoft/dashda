/**
 * 
 */
package com.dashda.enums;

/**
 * @author mhanafy
 *
 */
public enum PermissionTypeEnum {
	REST_SERVICE(1), CLIENT_TEMPLATE(2);
	
	private int value;
	
	PermissionTypeEnum(int value) {
		this.value = value;
		// TODO Auto-generated constructor stub
	}
	
	public int getValue() {
		return this.value;
	}
}
