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
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
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
	
	/* (non-Javadoc)
	 * @see com.dashda.service.components.VisitService#visitItemsList(java.lang.String)
	 */
	@Override
	public List<VisitDTO> visitItemsList(String username) {
		User user = userDao.findUserByUsername(username);
		
		visits = visitDao.findVisitItemsByEmployee(user.getEmployee());
		
		visitDTOs = new ArrayList<VisitDTO>();
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			
			visitDTO = new VisitDTO();
			
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
	public void completeVisits(String username, List<Integer> visits) {
		this.updateVisitStatus(visits, new Byte("1"));
		
	}

	@Override
	public void dicardVisits(String username, List<Integer> visits) {
		this.updateVisitStatus(visits, new Byte("0"));
		
	}

	private void updateVisitStatus(List<Integer> visits, byte status) {
		for (Iterator<Integer> iterator = visits.iterator(); iterator.hasNext();) {
			Integer visitId = (Integer) iterator.next();
			
			try {
			visit = visitDao.findVisitByIdAndNotComplete(visitId);
			
			visit.setCompleted(status);
			
				
				visitDao.updateVisit(visit);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
