/**
 * 
 */
package com.dashda.data.entities;

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
@Table(name = "PERMISSION_TYPE")
public class PermissionType {
	
	private int id;
	private String name;
	private Set<Permission> permissions = new HashSet<Permission>(0);
	
	
	/**
	 * 
	 */
	public PermissionType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param id
	 */
	public PermissionType(int id) {
		super();
		this.id = id;
	}


	@Id
	
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "NAME", length = 25)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissionType")
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	

}
