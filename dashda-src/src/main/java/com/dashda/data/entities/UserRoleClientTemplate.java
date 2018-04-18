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
@Table(name = "USER_ROLE_CLIENT_TEMPLATE")
public class UserRoleClientTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private UserRole userRole;
	private ClientTemplate clientTemplate;
	/**
	 * 
	 */
	public UserRoleClientTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param userRole
	 * @param clientTemplate
	 */
	public UserRoleClientTemplate(Integer id, UserRole userRole, ClientTemplate clientTemplate) {
		super();
		this.id = id;
		this.userRole = userRole;
		this.clientTemplate = clientTemplate;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ROLE_ID")
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENT_TEMPLATE_ID")
	public ClientTemplate getClientTemplate() {
		return clientTemplate;
	}
	public void setClientTemplate(ClientTemplate clientTemplate) {
		this.clientTemplate = clientTemplate;
	}
	
	

}
