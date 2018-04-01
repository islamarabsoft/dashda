package com.dashda.data.entities;
// Generated Apr 4, 2018 2:50:44 PM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Doctor generated by hbm2java
 */
@Entity
@Table(name = "DOCTOR")
public class Doctor implements java.io.Serializable {

	private int id;
	private Contact contact;
	private District district;
	private String doctorName;
	private String speciality;
	private String marketClass;
	private String center;
	private String nearestPharmacy;
	private String workingMorningPlace;
	private Byte active;
	private Set schedules = new HashSet(0);
	private Set visits = new HashSet(0);
	private Set employeesDoctors = new HashSet(0);
	
	public Doctor() {
	}

	public Doctor(int id) {
		this.id = id;
	}

	public Doctor(int id, Contact contact, District district, String doctorName, String speciality,
			String marketClass, String center, String nearestPharmacy, String workingMorningPlace, Byte active,
			Set schedules, Set visits, Set employeesDoctors) {
		this.id = id;
		this.contact = contact;
		this.district = district;
		this.doctorName = doctorName;
		this.speciality = speciality;
		this.marketClass = marketClass;
		this.center = center;
		this.nearestPharmacy = nearestPharmacy;
		this.workingMorningPlace = workingMorningPlace;
		this.active = active;
		this.schedules = schedules;
		this.visits = visits;
		this.employeesDoctors = employeesDoctors;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTACT_ID")
	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID")
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Column(name = "DOCTOR_NAME")
	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Column(name = "SPECIALITY", length = 45)
	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Column(name = "MARKET_CLASS", length = 45)
	public String getMarketClass() {
		return this.marketClass;
	}

	public void setMarketClass(String marketClass) {
		this.marketClass = marketClass;
	}

	@Column(name = "CENTER", length = 45)
	public String getCenter() {
		return this.center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	@Column(name = "NEAREST_PHARMACY", length = 45)
	public String getNearestPharmacy() {
		return this.nearestPharmacy;
	}

	public void setNearestPharmacy(String nearestPharmacy) {
		this.nearestPharmacy = nearestPharmacy;
	}

	@Column(name = "WORKING_MORNING_PLACE", length = 100)
	public String getWorkingMorningPlace() {
		return this.workingMorningPlace;
	}

	public void setWorkingMorningPlace(String workingMorningPlace) {
		this.workingMorningPlace = workingMorningPlace;
	}

	@Column(name = "ACTIVE")
	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set schedules) {
		this.schedules = schedules;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	public Set<Visit> getVisits() {
		return this.visits;
	}

	public void setVisits(Set visits) {
		this.visits = visits;
	}

	/**
	 * @return the employeesDoctor
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	public Set<EmployeeDoctor> getEmployeesDoctors() {
		return employeesDoctors;
	}

	/**
	 * @param employeesDoctor the employeesDoctor to set
	 */
	public void setEmployeesDoctors(Set employeesDoctors) {
		this.employeesDoctors = employeesDoctors;
	}
	
	

}
