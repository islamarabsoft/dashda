package com.dashda.data.entities;
// Generated Apr 4, 2018 2:50:44 PM by Hibernate Tools 5.2.8.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EmployeesCoveredDistrict generated by hbm2java
 */
@Entity
@Table(name = "EMPLOYEES_COVERED_DISTRICT")
public class EmployeesCoveredDistrict implements java.io.Serializable, com.dashda.data.entities.Entity {

	private Integer id;
	private District district;
	private Employee employee;

	public EmployeesCoveredDistrict() {
	}

	public EmployeesCoveredDistrict(Integer id) {
		this.id = id;
	}

	public EmployeesCoveredDistrict(Integer id, District district, Employee employee) {
		this.id = id;
		this.district = district;
		this.employee = employee;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID")
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
