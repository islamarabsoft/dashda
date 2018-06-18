/**
 * 
 */
package com.dashda.controllers.dto;

/**
 * @author mhanafy
 *
 */
public class VisitAdhocVisitOutputDTO implements AbstractDTO {

	private int visitId;
	private String date;
	private int serviceProviderId;
	
	
	public VisitAdhocVisitOutputDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VisitAdhocVisitOutputDTO(int visitId, String date, int serviceProviderId) {
		super();
		this.visitId = visitId;
		this.date = date;
		this.serviceProviderId = serviceProviderId;
	}
	public int getVisitId() {
		return visitId;
	}
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(int serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	
	
	
}
