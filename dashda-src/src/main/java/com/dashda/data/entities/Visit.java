package com.dashda.data.entities;
// Generated Apr 4, 2018 2:50:44 PM by Hibernate Tools 5.2.8.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Visit generated by hbm2java
 */
@Entity
@Table(name = "VISIT")
public class Visit implements java.io.Serializable,com.dashda.data.entities.BaseEntity {

	private int id;
	private ServiceProvider serviceProvider;
	private Employee employeeByEmployeeId;
	private Employee employeeBySubordinateId;
	private Date datetime;
	private String comment;
	private int doubleVisit;
	private VisitStatus visitStatus;
	private List<DoubleVisit> doubleVisits = new ArrayList<DoubleVisit>();
	private List<ProductVisit> productVisits = new ArrayList<ProductVisit>();

	public Visit() {
	}

	public Visit(int id) {
		this.id = id;
	}

	public Visit(int id, ServiceProvider serviceProvider, Employee employeeByEmployeeId, Employee employeeBySubordinateId, Date datetime,
			VisitStatus visitStatus) {
		this.id = id;
		this.serviceProvider = serviceProvider;
		this.employeeByEmployeeId = employeeByEmployeeId;
		this.employeeBySubordinateId = employeeBySubordinateId;
		this.datetime = datetime;
		this.visitStatus = visitStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false, insertable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_PROVIDER_ID")
	public ServiceProvider getServiceProvider() {
		return this.serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	public Employee getEmployeeByEmployeeId() {
		return this.employeeByEmployeeId;
	}

	public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
		this.employeeByEmployeeId = employeeByEmployeeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBORDINATE_ID")
	public Employee getEmployeeBySubordinateId() {
		return this.employeeBySubordinateId;
	}

	public void setEmployeeBySubordinateId(Employee employeeBySubordinateId) {
		this.employeeBySubordinateId = employeeBySubordinateId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME", length = 19)
	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@Column(name = "DOUBLE_VISIT", length = 1)
	public int getDoubleVisit() {
		return doubleVisit;
	}

	public void setDoubleVisit(int doubleVisit) {
		this.doubleVisit = doubleVisit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VISIT_STATUS_ID")
	public VisitStatus getVisitStatus() {
		return visitStatus;
	}

	public void setVisitStatus(VisitStatus visitStatus) {
		this.visitStatus = visitStatus;
	}

	@Column(name = "COMMENT", length = 100)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visit")
	public List<DoubleVisit> getDoubleVisits() {
		return doubleVisits;
	}

	public void setDoubleVisits(List<DoubleVisit> doubleVisits) {
		this.doubleVisits = doubleVisits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visit")
	public List<ProductVisit> getProductVisits() {
		return productVisits;
	}

	public void setProductVisits(List<ProductVisit> productVisits) {
		this.productVisits = productVisits;
	}




}
