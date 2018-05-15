/**
 * 
 */
package com.dashda.service.components;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashda.controllers.dto.AddOffVisitInputDTO;
import com.dashda.controllers.dto.AddOffVisitOutputDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.DeleteOffVisitInputDTO;
import com.dashda.controllers.dto.OffVisitReasonDTO;
import com.dashda.controllers.dto.UpdateOffVisitInputDTO;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.OffVisit;
import com.dashda.data.entities.OffVisitReason;
import com.dashda.data.entities.OffVisitStatus;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.OffVisitDTO;
import com.dashda.data.repositories.OffVisitDao;
import com.dashda.data.repositories.OffVisitReasonDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.enums.OffVisitStatusEnum;
import com.dashda.exception.OffVisitServiceException;
import com.dashda.utilities.DateUtilities;

/**
 * @author mhanafy
 *
 */
@Service
public class OffVisitServiceImpl extends ServicesManager implements OffVisitService {

	@Autowired
	OffVisitDao offVisitDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OffVisitReasonDao offVisitReasonDao;
	
	
	@Override
	public AppResponse createOffVisit(String username, 
				AddOffVisitInputDTO addOffVisitInputDTO) 
							throws OffVisitServiceException, ParseException{
		
		return createResponse(persistOffVisit(username, 0, 
				DateUtilities.convertToDate(addOffVisitInputDTO.getDate(), DateUtilities.DATE_FORMATE_PATTERN), 
				addOffVisitInputDTO.getReasonId(), addOffVisitInputDTO.getComment()), "Off Visit Created Successfully");
	}

	
	@Override
	public AppResponse updateOffVisit(String username, 
				UpdateOffVisitInputDTO updateOffVisitInputDTO) 
							throws OffVisitServiceException, ParseException{

		return createResponse(persistOffVisit(username, updateOffVisitInputDTO.getOffVisitId(), 
				DateUtilities.convertToDate(updateOffVisitInputDTO.getDate(), DateUtilities.DATE_FORMATE_PATTERN), 
				updateOffVisitInputDTO.getReasonId(), updateOffVisitInputDTO.getComment()), "Off Visit updated Successfully");
	}
	
	
	

	private AddOffVisitOutputDTO persistOffVisit(String username, int offVisitId, Date scheduleDate
			, int offVisitReasonId, String comment) throws OffVisitServiceException {

		if(DateUtilities.compareTwoDates(new Date(), scheduleDate) == -1)
			throw new OffVisitServiceException(ERROR_CODE_1013);
		
		User user = userDao.findUserByUsername(username);
		Employee employee = user.getEmployee();
		if(employee == null)
			throw new OffVisitServiceException(ERROR_CODE_1001);
		
		OffVisit offVisit = null;
		
		if(offVisitId == 0) {
			offVisit = offVisitDao.findOffVisit(employee, 
			scheduleDate, offVisitReasonId);
			
			if(offVisit != null)
				throw new OffVisitServiceException(ERROR_CODE_1023);
		}else {
			offVisit = offVisitDao.findOffVisit(offVisitId);
			if(offVisit == null)
				throw new OffVisitServiceException(ERROR_CODE_1024);
			if(offVisit.getOffVisitStatus().getId() == OffVisitStatusEnum.ACCEPTED.getValue() 
					|| offVisit.getOffVisitStatus().getId() == OffVisitStatusEnum.REJECTED.getValue()) 
				throw new OffVisitServiceException(ERROR_CODE_1025);
		}
		
		if(offVisitId == 0) {
			offVisit = new OffVisit();
			offVisit.setEmployee(employee);
			offVisit.setManager(employee.getManager());
			offVisit.setOffVisitReason(new OffVisitReason(offVisitReasonId));
			offVisit.setOffVisitStatus(new OffVisitStatus(OffVisitStatusEnum.PENDING_APPROVAL.getValue()));
			offVisit.setDateTime(scheduleDate);
			offVisit.setComment(comment);
		}else {
			offVisit.setOffVisitReason(new OffVisitReason(offVisitReasonId));
			offVisit.setDateTime(scheduleDate);
			offVisit.setComment(comment);
		}
		
		
		
		offVisitDao.createOffVisit(offVisit);
		
		AddOffVisitOutputDTO addOffVisitOutputDTO = new AddOffVisitOutputDTO();
		
		addOffVisitOutputDTO.setDate(offVisit.getDateTime()+"");
		addOffVisitOutputDTO.setOffVisitId(offVisit.getId());
		addOffVisitOutputDTO.setReasonId(offVisit.getOffVisitReason().getId());
		
		return addOffVisitOutputDTO;
	}


	@Override
	public AppResponse deleteOffVisit(String username, DeleteOffVisitInputDTO deleteOffVisitInputDTO)
			throws OffVisitServiceException {
		
		OffVisit offVisit = offVisitDao.findOffVisit(deleteOffVisitInputDTO.getOffVisitId());
		if(offVisit == null)
			throw new OffVisitServiceException(ERROR_CODE_1024);
		
		if(offVisit.getOffVisitStatus().getId() == OffVisitStatusEnum.ACCEPTED.getValue() 
				|| offVisit.getOffVisitStatus().getId() == OffVisitStatusEnum.REJECTED.getValue()) 
			throw new OffVisitServiceException(ERROR_CODE_1025);
		
		offVisitDao.removeOffVisit(offVisit);
		return emptyResponse("Off Visit Deleted Successfully");
	}


	@Override
	public AppResponse getOffVisitreasons() {
		List reasons = offVisitReasonDao.findAllReasons();
		
		List offVisitReasonDTOs = new ArrayList<OffVisitReasonDTO>();
		
		for (Iterator iterator = reasons.iterator(); iterator.hasNext();) {
			OffVisitReason offVisitReason = (OffVisitReason) iterator.next();
			
			OffVisitReasonDTO offVisitReasonDTO = new OffVisitReasonDTO();
			offVisitReasonDTO.setOffVisitReasonId(offVisitReason.getId());
			offVisitReasonDTO.setOffVisitreasonName(offVisitReason.getName());
			offVisitReasonDTOs.add(offVisitReasonDTO);
		}
		return okListResponse(offVisitReasonDTOs, "List Size: " + reasons.size());
	}


	@Override
	public AppResponse getListofOffVisit(String username) 
				throws OffVisitServiceException, ParseException {
		
		List offVisitDTOs = new ArrayList();
		User user = userDao.findUserByUsername(username);
		
		List<OffVisit> offVisits = offVisitDao.findOffVisitsAfterToday(user.getEmployee());
		
		for (Iterator iterator = offVisits.iterator(); iterator.hasNext();) {
			OffVisit offVisit = (OffVisit) iterator.next();
			OffVisitDTO offVisitDTO = new OffVisitDTO();
			
			offVisitDTO.setEmployeeName(offVisit.getEmployee().getContact().getFirstName()
					+ " "+ offVisit.getEmployee().getContact().getFirstName());
			offVisitDTO.setOffVisitId(offVisit.getId());
			offVisitDTO.setOffVisitReason(offVisit.getOffVisitReason().getName());
			offVisitDTO.setOffVisitReasonId(offVisit.getOffVisitReason().getId());
			offVisitDTO.setOffVisitDate(DateUtilities.dateFormate(offVisit.getDateTime()));
			offVisitDTO.setStatus(offVisit.getOffVisitStatus().getName());
			offVisitDTO.setStatusId(offVisit.getOffVisitStatus().getId());
			offVisitDTO.setComment(offVisit.getComment());
			
			offVisitDTOs.add(offVisitDTO);
		}
		
		return okListResponse(offVisitDTOs, "List Size: " + offVisitDTOs.size());
	}

}
