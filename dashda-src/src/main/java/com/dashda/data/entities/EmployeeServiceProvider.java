/**
 * 
 */
package com.dashda.data.entities;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "EMPLOYEE_SERVICE_PROVIDER")
public class EmployeeServiceProvider implements java.io.Serializable, com.dashda.data.entities.BaseEntity{
	
	/**
	 * 
	 */
	private int id;
	private Employee employee;
	private ServiceProvider serviceProvider;
	
	
	/**
	 * 
	 */
	public EmployeeServiceProvider() {
	}
	/**
	 * @param id
	 */
	public EmployeeServiceProvider(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param employee
	 * @param doctor
	 */
	public EmployeeServiceProvider(int id, Employee employee, ServiceProvider serviceProvider) {
		this.id = id;
		this.employee = employee;
		this.serviceProvider = serviceProvider;
	}
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	public int getId() {
		return this.id;
	}
	
	
	/**
	 * @param employee
	 * @param doctor
	 */
	public EmployeeServiceProvider(Employee employee, ServiceProvider serviceProvider) {
		this.employee = employee;
		this.serviceProvider = serviceProvider;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_PROVIDER_ID")
	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
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
