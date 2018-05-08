/**
 * 
 */
package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;

/**
 * @author mhanafy
 *
 */
public class UnAssignServiceProviderInputDTO implements AbstractDTO {

	@Digits(fraction=0, integer=8)
	private int assignedId;

	public int getAssignedId() {
		return assignedId;
	}

	public void setAssignedId(int assignedId) {
		this.assignedId = assignedId;
	}


	
	
}
