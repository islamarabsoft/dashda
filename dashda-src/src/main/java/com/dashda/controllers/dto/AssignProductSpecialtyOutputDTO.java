/**
 * 
 */
package com.dashda.controllers.dto;

import java.util.List;

/**
 * @author mohamed.hanfy
 *
 */
public class AssignProductSpecialtyOutputDTO implements AbstractDTO {

	private List<Integer> assignIds;

	public List<Integer> getAssignIds() {
		return assignIds;
	}

	public void setAssignIds(List<Integer> assignIds) {
		this.assignIds = assignIds;
	}
	
	
}
