/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.VisitAddCommentInputDTO;
import com.dashda.controllers.dto.VisitCompleteInputDTO;
import com.dashda.controllers.dto.VisitDTO;
import com.dashda.controllers.dto.VisitListInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.entities.VisitStatus;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.enums.VisitStatusEnum;
import com.dashda.exception.VisitServiceException;
import com.dashda.utilities.DateUtilities;


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
	
	private List visitDTOs;

	private VisitDTO visitDTO;

	private Visit visit;
	
	private Employee employee;

	private User user;
	
	/* (non-Javadoc)
	 * @see com.dashda.service.components.VisitService#visitItemsList(java.lang.String)
	 */
	@Override
	public AppResponse visitItemsList(String username, VisitListInputDTO visitInquiryDTO) throws VisitServiceException, ParseException {
		User user = userDao.findUserByUsername(username);
		
		employee = user.getEmployee();
		if(employee == null)
			throw new VisitServiceException(ERROR_CODE_1001);
		
		visits = visitDao.findVisitInPeriodItemsByEmployee(employee, DateUtilities.convertToDate(visitInquiryDTO.getFrom(), DateUtilities.DATE_FORMATE_PATTERN),
				DateUtilities.convertToDate(visitInquiryDTO.getTo(), DateUtilities.DATE_FORMATE_PATTERN), visitInquiryDTO.getServiceTypeId());
		
		visitDTOs = new ArrayList();
		
		String status = null;
		String statusId = null;
		String doctorName = null;
		
		for (Iterator iterator = visits.iterator(); iterator.hasNext();) {
			Visit visit = (Visit) iterator.next();
			if(visit.getVisitStatus().getId() == VisitStatusEnum.PLANNED.getValue()) {
				statusId = "1";
				status = "Not Completed";
			}else if(visit.getVisitStatus().getId() == VisitStatusEnum.COMPLETE.getValue()){
				statusId = "2";
				status = "Completed";
			}else if(visit.getVisitStatus().getId() == VisitStatusEnum.CANCELED.getValue()){
				statusId = "3";
				status = "Discard";
			}
			
			
			doctorName = visit.getServiceProvider().getEnName();
			
			visitDTO = new VisitDTO();
			
			visitDTO.setVisitId(visit.getId());
			visitDTO.setServiceProviderId(visit.getServiceProvider().getId());
			visitDTO.setServiceProviderTypeId(visit.getServiceProvider().getServiceProviderType().getId()+"");
			visitDTO.setEmployeeId(visit.getEmployeeByEmployeeId().getId());
			visitDTO.setServiceProviderName(doctorName);
			visitDTO.setEmployeeName(visit.getEmployeeByEmployeeId().getContact().getFirstName() 
							+ " " + visit.getEmployeeByEmployeeId().getContact().getLastName());		
			visitDTO.setVisitDate(visit.getDatetime()+"");
			visitDTO.setStatus(status);
			visitDTO.setStatusId(statusId);
			visitDTO.setComment(visit.getComment());
			
			
			visitDTOs.add(visitDTO);
		}
		
		return okListResponse(visitDTOs, "List Size: " + visitDTOs.size() );
	}

	@Override
	public AppResponse completeVisit(String username, VisitCompleteInputDTO visitCompleteInput) throws VisitServiceException {
		
		user = userDao.findUserByUsername(username);
		
		this.updateVisitStatus(user, visitCompleteInput.getId(), visitCompleteInput.getComment(), VisitStatusEnum.COMPLETE.getValue());
		
		return emptyResponse("Visits status was updated successfully");
	}



	private void updateVisitStatus(User user, int visitId, String comment, int status) throws VisitServiceException {
		employee = user.getEmployee();
		
		if(employee == null)
			throw new VisitServiceException(ERROR_CODE_1001);
		
			visit = visitDao.findUserVisitByIdAndNotComplete(visitId, employee.getId());
			
			if(visit == null)
				throw new VisitServiceException(ERROR_CODE_1004);
			if(status != 0) {
				visit.setVisitStatus(new VisitStatus(status));
				if(DateUtilities.compareTwoDates(new Date(), visit.getDatetime()) == 1)
					throw new VisitServiceException(ERROR_CODE_1019);
			}
				
			
			if(comment != null && !comment.equals(""))
				visit.setComment(comment);
			if(visit.getComment() == null)
				throw new VisitServiceException(ERROR_CODE_1018);
			
			visitDao.updateVisit(visit);
		
	}

	@Override
	public AppResponse addComment(String username, VisitAddCommentInputDTO visitAddCommentInputDTO)
			throws VisitServiceException {
		user = userDao.findUserByUsername(username);
		this.updateVisitStatus(user, visitAddCommentInputDTO.getId(), visitAddCommentInputDTO.getComment(), 0);
		
		return emptyResponse("Add Comment on Visit Successfully");
	}

	@Override
	public void discardAllVisitsItemsBeforeHoursDuration(int hours) {
		Date date = DateUtilities.getZeroTimeDate(new Date());
		Date executionDate = DateUtilities.decreaseDateByHours(date, hours);
		log.info("Current Date is {"+date+"}");
		log.info("Engin Should Discard All Not Completed Visits Before {"+hours+"} Hours");
		log.info("Engin Should Discard All Not Completed Visits Before {"+executionDate+"}");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	@Override
	public AppResponse dicardVisits(String username, VisitCompleteInput visitCompleteInput) throws VisitServiceException {
		List<Integer> visits = requestInput.getIds();
		user = userDao.findUserByUsername(username);
		
		this.updateVisitStatus(user, visits, VisitStatusEnum.CANCELED.getValue());
		return emptyResponse("Visits status was updated successfully");
		
	}**/


}
