/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mohamed.hanfy
 *
 */
public class ReportUnVisitOutputDTO implements AbstractDTO {

	private String firstName;
	private String lastName;
	private String specialty;
	private String district;
	private String targetVisits;
	
	
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
