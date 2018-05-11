/**
 * 
 */
package com.dashda.data.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;

/**
 * @author mhanafy
 *
 */
@Entity
@Table(name="REQUEST")
public class Request implements Serializable, com.dashda.data.entities.BaseEntity {

	private int id;
	private RequestType requestType;
	private RequestStatus requestStatus;
	private Employee createdBy;
	private Date createdAt;
	
	
	public Request(int id) {
		this.id = id;
	}


	public Request() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUEST_TYPE_ID")
	public RequestType getRequestType() {
		return requestType;
	}


	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUEST_STATUS_ID")
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}


	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	public Employee getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT", length = 19, insertable=false, updatable=false)
	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
}
