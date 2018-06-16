/**
 * 
 */
package com.dashda.data.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author mhanafy
 *
 */
@Entity
@Table(name= "SPECIALTY")
public class Specialty implements Serializable, com.dashda.data.entities.BaseEntity{

	private Integer id;
	private String name;
	private Set serviceProviders = new HashSet(0);
	private Set productSpecialties = new HashSet(0);
	/**
	 * 
	 */
	public Specialty() {
		super();
	}
	/**
	 * @param id
	 */
	public Specialty(Integer id) {
		super();
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param serviceProviders
	 */
	public Specialty(Integer id, String name, Set serviceProviders) {
		super();
		this.id = id;
		this.name = name;
		this.serviceProviders = serviceProviders;
	}
	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialty")
	public Set<ServiceProvider> getServiceProviders() {
		return serviceProviders;
	}

	public void setServiceProviders(Set serviceProviders) {
		this.serviceProviders = serviceProviders;
	}

	
}
