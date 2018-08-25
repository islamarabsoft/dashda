/**
 * 
 */
package com.dashda.controllers.dto.plan;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mhanafy
 *
 */
public class DeletePlanItemInputDTO implements AbstractDTO {

	private int planItemId;

	public int getPlanItemId() {
		return planItemId;
	}

	public void setPlanItemId(int planItemId) {
		this.planItemId = planItemId;
	}
	
	
	
}
