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
@Table(name="PRODUCT_SPECIALTY")
public class ProductSpecialty implements Serializable, BaseEntity {

	private int id;
	private Product product;
	private Specialty specialty;
	
	
	public ProductSpecialty() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ProductSpecialty(Product product, Specialty specialty) {
		super();
		this.product = product;
		this.specialty = specialty;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPECIALTY_ID")
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	
	
}
