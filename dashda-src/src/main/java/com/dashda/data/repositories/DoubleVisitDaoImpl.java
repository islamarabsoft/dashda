/**
 * 
 */
package com.dashda.data.repositories;

import org.springframework.stereotype.Repository;

import com.dashda.data.entities.DoubleVisit;

/**
 * @author mhanafy
 *
 */
@Repository
public class DoubleVisitDaoImpl extends AbstractDao implements DoubleVisitDao {

	public DoubleVisitDaoImpl() {
		this.setDAOClass(DoubleVisit.class);
	}

	public void addDoubleVisit(DoubleVisit doubleVisit) {
		this.save(doubleVisit);
	}
	
}
