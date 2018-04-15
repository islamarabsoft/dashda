/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.VisitDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.exception.VisitServiceException;
import com.google.appengine.repackaged.org.antlr.runtime.EarlyExitException;


/**
 * @author mhanafy
 *
 */
@Service
public class VisitServiceImpl extends ServicesManager implements VisitService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	VisitDao visitDao;

	private List<Visit> visits;
	
	private List<VisitDTO> visitDTOs;

	private VisitDTO visitDTO;

	private Visit visit;
	
	private Employee employee;

	private User user;
	
	/* (non-Javadoc)
	 * @see com.dashda.service.components.VisitService#visitItemsList(java.lang.String)
	 */
	@Override
	public List<VisitDTO> visitItemsList(String username) throws VisitServiceException {
		User user = userDao.findUserByUsername(username);
		
		employee = user.getEmployee();
		if(employee == null)
			throw new VisitServiceException(ERROR_CODE_1001);
		
		visits = visitDao.findVisitItemsByEmployee(user.getEmployee());
		
		visitDTOs = new ArrayList<VisitDTO>();
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			
			visitDTO = new VisitDTO();
			
			visitDTO.setVisitId(visit.getId());
			visitDTO.setDoctorId(visit.getDoctor().getId());
			visitDTO.setEmployeeId(visit.getEmployeeByEmployeeId().getId());
			visitDTO.setEmployeeName(visit.getEmployeeByEmployeeId().getContact().getFirstName() 
							+ " " + visit.getEmployeeByEmployeeId().getContact().getLastName());		
			visitDTO.setVisitDate(visit.getDatetime()+"");
			
			visitDTOs.add(visitDTO);
		}
		
		return visitDTOs;
	}

	@Override
	public void completeVisits(String username, List<Integer> visits) throws NumberFormatException, VisitServiceException {
		user = userDao.findUserByUsername(username);
		
		this.updateVisitStatus(user, visits, new Byte("1"));
		
	}

	@Override
	public void dicardVisits(String username, List<Integer> visits) throws NumberFormatException, VisitServiceException {
		user = userDao.findUserByUsername(username);
		
		this.updateVisitStatus(user, visits, new Byte("0"));
		
	}

	private void updateVisitStatus(User user, List<Integer> visits, byte status) throws VisitServiceException {
		employee = user.getEmployee();
		
		if(employee == null)
			throw new VisitServiceException(ERROR_CODE_1001);
		
		for (Iterator<Integer> iterator = visits.iterator(); iterator.hasNext();) {
			Integer visitId = (Integer) iterator.next();
		
			visit = visitDao.findUserVisitByIdAndNotComplete(visitId, employee.getId());
			
			if(visit == null)
				throw new VisitServiceException(ERROR_CODE_1004 +" '" +visitId+"'");
			
			visit.setCompleted(status);
			visitDao.updateVisit(visit);
		}
	}
}
