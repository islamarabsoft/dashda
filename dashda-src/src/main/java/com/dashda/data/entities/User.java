package com.dashda.data.entities;
// Generated Apr 4, 2018 2:50:44 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
public class User implements java.io.Serializable {

	private Integer id;
	private Contact contact;
	private Employee employee;
	private UserRole userRole;
	private String username;
	private String password;
	private byte active;
	
	private Date createdAt;

	public User() {
	}

	public User(Contact contact, UserRole userRole, String username, String password, byte active,
			Date createdAt) {
		this.contact = contact;
		this.userRole = userRole;
		this.username = username;
		this.password = password;
		this.active = active;
		this.createdAt = createdAt;
	}

	public User(Contact contact, Employee employee, UserRole userRole, String username,
			String password, byte active, Date createdAt) {
		this.contact = contact;
		this.employee = employee;
		this.userRole = userRole;
		this.username = username;
		this.password = password;
		this.active = active;
		this.createdAt = createdAt;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTACT_ID", nullable = false)
	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ROLE_ID", nullable = false)
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Column(name = "USERNAME", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ACTIVE", nullable = false, insertable = false)
	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT", nullable = false, length = 19, insertable = false, updatable = false)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
