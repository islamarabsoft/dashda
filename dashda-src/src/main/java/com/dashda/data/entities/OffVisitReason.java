/**
 * 
 */
package com.dashda.data.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author mhanafy
 *
 */
@Entity
@Table(name="OFF_VISIT_REASON")
public class OffVisitReason implements Serializable, com.dashda.data.entities.BaseEntity {

	private int id;
	private String name;
	private Set offVisitReasons = new HashSet(0);
	
	public OffVisitReason() {
		super();
	}

	public OffVisitReason(int id) {
		super();
		this.id = id;
	}

	@Id

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "offVisitReason")
	public Set<OffVisit> getOffVisitReasons() {
		return offVisitReasons;
	}

	public void setOffVisitReasons(Set offVisitReasons) {
		this.offVisitReasons = offVisitReasons;
	}
}
