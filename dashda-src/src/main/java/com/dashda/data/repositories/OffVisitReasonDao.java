package com.dashda.data.repositories;

import java.util.List;

import com.dashda.data.entities.OffVisitReason;

public interface OffVisitReasonDao {

	
	public List<OffVisitReason> findAllReasons();
}
