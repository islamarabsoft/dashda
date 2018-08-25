/**
 * 
 */
package com.dashda.controllers.dto.visit;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class VisitDetailInputDTO implements AbstractDTO {

	private int visitId;

	public VisitDetailInputDTO() {
		super();
	}

	public VisitDetailInputDTO(int visitId) {
		super();
		this.visitId = visitId;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	
	
}
