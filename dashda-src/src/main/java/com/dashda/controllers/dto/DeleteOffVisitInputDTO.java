package com.dashda.controllers.dto;

import javax.validation.constraints.Digits;

public class DeleteOffVisitInputDTO implements AbstractDTO {

	@Digits(fraction=0, integer=8) 
	private int offVisitId;

	public int getOffVisitId() {
		return offVisitId;
	}

	public void setOffVisitId(int offVisitId) {
		this.offVisitId = offVisitId;
	}
	
	
	
}
