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
public class VisitDetailOutpoutDTO implements AbstractDTO {
	
	private String datetime;
	private String specialty;
	private String brick;
	private String serviceProvider;
	private String serviceProviderType;
	private List<String> products;
	private String isDoubleVisit;
	private List<String> managers;
	private String comment;
	
	
	public VisitDetailOutpoutDTO() {
		super();
	}


	public VisitDetailOutpoutDTO(String datetime, String specialty, String brick, String serviceProvider,
			String serviceProviderType, List<String> products, String isDoubleVisit, List<String> managers,
			String comment) {
		super();
		this.datetime = datetime;
		this.specialty = specialty;
		this.brick = brick;
		this.serviceProvider = serviceProvider;
		this.serviceProviderType = serviceProviderType;
		this.products = products;
		this.isDoubleVisit = isDoubleVisit;
		this.managers = managers;
		this.comment = comment;
	}


	public String getDatetime() {
		return datetime;
	}


	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public String getBrick() {
		return brick;
	}


	public void setBrick(String brick) {
		this.brick = brick;
	}


	public String getServiceProvider() {
		return serviceProvider;
	}


	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}


	public String getServiceProviderType() {
		return serviceProviderType;
	}


	public void setServiceProviderType(String serviceProviderType) {
		this.serviceProviderType = serviceProviderType;
	}


	public List<String> getProducts() {
		return products;
	}


	public void setProducts(List<String> products) {
		this.products = products;
	}


	public String getIsDoubleVisit() {
		return isDoubleVisit;
	}


	public void setIsDoubleVisit(String isDoubleVisit) {
		this.isDoubleVisit = isDoubleVisit;
	}


	public List<String> getManagers() {
		return managers;
	}


	public void setManagers(List<String> managers) {
		this.managers = managers;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	
	
	
	

}
