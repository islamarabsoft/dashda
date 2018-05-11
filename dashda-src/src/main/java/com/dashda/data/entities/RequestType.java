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
@Table(name="REQUEST_TYPE")
public class RequestType implements Serializable, com.dashda.data.entities.BaseEntity {

	private int id;
	private String name;
	private Set request = new HashSet(0);
	
	public RequestType(int id) {
		super();
		this.id = id;
	}

	public RequestType() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requestType")
	public Set<Request> getRequest() {
		return request;
	}

	public void setRequest(Set request) {
		this.request = request;
	}
	
	
	
}
