/**
 * 
 */
package com.dashda.data.repositories;

import org.springframework.stereotype.Repository;

import com.dashda.data.entities.ProductVisit;

/**
 * @author mhanafy
 *
 */
@Repository
public class ProductVisitDaoImpl extends AbstractDao implements ProductVisitDao {

	
	public ProductVisitDaoImpl() {
		this.setDAOClass(ProductVisit.class);
	}

	@Override
	public void addProductVisit(ProductVisit productVisit) {
		save(productVisit);
	}

}
