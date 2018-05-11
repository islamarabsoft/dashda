package com.dashda.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dashda.data.entities.OffVisitReason;

@Repository
public class OffVisitReasonDaoImpl extends AbstractDao implements OffVisitReasonDao {

	
	
	public OffVisitReasonDaoImpl() {
		super();
		this.setDAOClass(OffVisitReason.class);
	}

	@Override
	public List<OffVisitReason> findAllReasons() {
		return findAll();
	}

	
}
