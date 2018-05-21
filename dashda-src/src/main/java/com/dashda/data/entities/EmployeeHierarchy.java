/**
 * 
 */
package com.dashda.data.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author mhanafy
 *
 */
@Entity
@Table(name="EMPLOYEE_HIERARCHY")
public class EmployeeHierarchy implements Serializable, BaseEntity {

	private int id;
	private Employee employee;
	private Employee manager;
	private int structureLevel;
	
	
	public EmployeeHierarchy() {
	}
	
	public EmployeeHierarchy(Employee employee) {
		this.employee = employee;
	}
	
	public EmployeeHierarchy(Employee employee, Employee manager) {
		this.employee = employee;
		this.manager = manager;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@JoinColumn(name = "MANAGER_ID")
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	@Column(name = "STRUCTURE_LEVEL")
	public int getStructureLevel() {
		return structureLevel;
	}
	public void setStructureLevel(int structureLevel) {
		this.structureLevel = structureLevel;
	}
	
	
	
}
