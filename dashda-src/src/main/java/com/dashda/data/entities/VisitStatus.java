/**
 * 
 */
package com.dashda.data.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author mhanafy
 *
 */
@Entity
@Table(name= "VISIT_STATUS")
public class VisitStatus implements Serializable, com.dashda.data.entities.BaseEntity{

	private int id;
	private String name;
	private Set visits = new HashSet(0);
	/**
	 * 
	 */
	public VisitStatus() {
	}
	/**
	 * @param id
	 */
	public VisitStatus(int id) {
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param visit
	 */
	public VisitStatus(int id, String name, Set visits) {
		this.id = id;
		this.name = name;
		this.visits = visits;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name = "NAME", length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "visitStatus")
	public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set visits) {
		this.visits = visits;
	}


}
