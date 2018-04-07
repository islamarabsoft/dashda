/**
 * 
 */
package com.dashda.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author mhanafy
 *
 */
@Entity
@Table(name = "EMPLOYEE_DOCTOR")
public class EmployeeDoctor implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Employee employee;
	private Doctor doctor;
	
	
	/**
	 * 
	 */
	public EmployeeDoctor() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 */
	public EmployeeDoctor(int id) {
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param employee
	 * @param doctor
	 */
	public EmployeeDoctor(int id, Employee employee, Doctor doctor) {
		this.id = id;
		this.employee = employee;
		this.doctor = doctor;
	}
	/**
	 * @return the id
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}
	
	
	/**
	 * @param employee
	 * @param doctor
	 */
	public EmployeeDoctor(Employee employee, Doctor doctor) {
		this.employee = employee;
		this.doctor = doctor;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTOR_ID")
	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
