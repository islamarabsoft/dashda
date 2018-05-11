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

/**
 * 
 */

/**
 * @author mhanafy
 *
 */
@Entity
@Table(name="OFF_VISIT")
public class OffVisit implements Serializable, BaseEntity {
	private int id;
	private Date dateTime;
	private OffVisitReason offVisitReason;
	private Employee employee;
	private Employee manager;
	private OffVisitStatus offVisitStatus;
	private String comment;
	private Date createdAt;
	
	public OffVisit(int id) {
		super();
		this.id = id;
	}


	public OffVisit() {
		super();
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME", length = 19)
	public Date getDateTime() {
		return dateTime;
	}


	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFF_VISIT_REASON_ID")
	public OffVisitReason getOffVisitReason() {
		return offVisitReason;
	}


	public void setOffVisitReason(OffVisitReason offVisitReason) {
		this.offVisitReason = offVisitReason;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFF_VISIt_STATUS_ID")
	public OffVisitStatus getOffVisitStatus() {
		return offVisitStatus;
	}


	public void setOffVisitStatus(OffVisitStatus offVisitStatus) {
		this.offVisitStatus = offVisitStatus;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	public Employee getManager() {
		return manager;
	}


	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@Column(name = "COMMENT", length = 200)
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
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
