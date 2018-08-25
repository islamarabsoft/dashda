/**
 * 
 */
package com.dashda.controllers.dto.report;

import com.dashda.controllers.dto.AbstractDTO;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportTargetVisitOutputDTO implements AbstractDTO {

	private String firstName;
	private String lastName;
	private String specialty;
	private String district;
	private String targetVisits;
	
	
	public ReportTargetVisitOutputDTO() {
		super();
	}


	public ReportTargetVisitOutputDTO(String firstName, String lastName, String specialty, String district,
			String targetVisits) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.district = district;
		this.targetVisits = targetVisits;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getTargetVisits() {
		return targetVisits;
	}


	public void setTargetVisits(String targetVisits) {
		this.targetVisits = targetVisits;
	}
	
	
	
}
