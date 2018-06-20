/**
 * 
 */
package com.dashda.service.components;

import java.util.List;

import com.dashda.controllers.dto.AbstractDTO;

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
