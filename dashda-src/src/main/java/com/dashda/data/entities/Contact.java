package com.dashda.data.entities;
// Generated Apr 4, 2018 2:50:44 PM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
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

/**
 * Contact generated by hbm2java
 */
@Entity
@Table(name = "CONTACT")
public class Contact implements java.io.Serializable {

	private Integer id;
	private District district;
	private Governorate governorate;
	private String firstName;
	private String lastName;
	private String address;
	private String phone1;
	private String phone2;
	private String phone3;
	private String phone4;
	private String phone5;
	private String phone6;
	private Set users = new HashSet(0);
	private Set employees = new HashSet(0);

	public Contact() {
	}

	public Contact(District district, Governorate governorate) {
		this.district = district;
		this.governorate = governorate;
	}

	public Contact(District district, Governorate governorate, String firstName, String lastName, String address,
			String phone1, String phone2, String phone3, String phone4, String phone5, String phone6, Set users,
			Set employees) {
		this.district = district;
		this.governorate = governorate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.phone4 = phone4;
		this.phone5 = phone5;
		this.phone6 = phone6;
		this.users = users;
		this.employees = employees;
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
	@JoinColumn(name = "DISTRICT_ID", nullable = false)
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOVERNORATE_ID", nullable = false)
	public Governorate getGovernorate() {
		return this.governorate;
	}

	public void setGovernorate(Governorate governorate) {
		this.governorate = governorate;
	}

	@Column(name = "FIRST_NAME", length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PHONE_1", length = 13)
	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "PHONE_2", length = 13)
	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@Column(name = "PHONE_3", length = 11)
	public String getPhone3() {
		return this.phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	@Column(name = "PHONE_4", length = 11)
	public String getPhone4() {
		return this.phone4;
	}

	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}

	@Column(name = "PHONE_5", length = 11)
	public String getPhone5() {
		return this.phone5;
	}

	public void setPhone5(String phone5) {
		this.phone5 = phone5;
	}

	@Column(name = "PHONE_6", length = 11)
	public String getPhone6() {
		return this.phone6;
	}

	public void setPhone6(String phone6) {
		this.phone6 = phone6;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	/**
	 * @return the employees
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
	public Set<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(Set employees) {
		this.employees = employees;
	}

}
