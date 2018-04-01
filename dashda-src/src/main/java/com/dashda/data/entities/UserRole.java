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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UserRole generated by hbm2java
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole implements java.io.Serializable {

	private Integer id;
	private String name;
	private String userRole;
	private Set userRolePermissions = new HashSet(0);
	private Set users = new HashSet(0);

	public UserRole() {
	}

	public UserRole(String name, String userRole) {
		this.name = name;
		this.userRole = userRole;
	}

	public UserRole(String name, String userRole, Set userRolePermissions, Set users) {
		this.name = name;
		this.userRole = userRole;
		this.userRolePermissions = userRolePermissions;
		this.users = users;
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

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USER_ROLE", nullable = false, length = 45)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
	public Set<UserRolePermission> getUserRolePermissions() {
		return this.userRolePermissions;
	}

	public void setUserRolePermissions(Set userRolePermissions) {
		this.userRolePermissions = userRolePermissions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
	public Set<UserRole> getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}
