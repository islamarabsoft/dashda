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
@Table(name="PRODUCT")
public class Product implements BaseEntity, Serializable {

	private int id;
	private String name;
	private Account account;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id) {
		super();
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false, updatable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "NAME", length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
}
