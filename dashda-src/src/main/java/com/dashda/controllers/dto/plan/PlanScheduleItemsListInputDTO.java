/**
 * 
 */
package com.dashda.controllers.dto.plan;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class PlanScheduleItemsListInputDTO implements AbstractDTO {
	
	private int planId;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}
	
	

}
