package com.dashda.data.repositories;

import java.util.Date;
import java.util.List;

import com.dashda.data.entities.Employee;
import com.dashda.data.entities.OffVisit;
import com.dashda.data.entities.OffVisitReason;

public interface OffVisitDao {

	public OffVisit findOffVisit(Employee employee, Date date, int reasonId);

	public void createOffVisit(OffVisit offVisit);

	public OffVisit findOffVisit(int offVisitId);

	public void removeOffVisit(OffVisit offVisit);

	public List<OffVisit> findPendingApproval(int managerId);

	public List<OffVisit> findOffVisitsAfterToday(Employee employee);

}
