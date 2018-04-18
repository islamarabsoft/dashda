/**
 * 
 */
package com.dashda.data.entities;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "CLIENT_TEMPLATE")
public class ClientTemplate implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String template;
	private Set<UserRoleClientTemplate> userRoleClientTemplate;
	
	/**
	 * 
	 */
	public ClientTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param template
	 */
	public ClientTemplate(Integer id, String template) {
		super();
		this.id = id;
		this.template = template;
	}
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the template
	 */
	@Column(name = "TEMPLATE", length = 45)
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientTemplate")
	public Set<UserRoleClientTemplate> getUserRoleClientTemplate() {
		return userRoleClientTemplate;
	}
	public void setUserRoleClientTemplate(Set<UserRoleClientTemplate> userRoleClientTemplate) {
		this.userRoleClientTemplate = userRoleClientTemplate;
	}
	
	

}
