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
import org.springframework.transaction.annotation.Transactional;

import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.VisitAddCommentInputDTO;
import com.dashda.controllers.dto.VisitAdhocVisitInputDTO;
import com.dashda.controllers.dto.VisitAdhocVisitOutputDTO;
import com.dashda.controllers.dto.VisitCompleteInputDTO;
import com.dashda.controllers.dto.VisitDTO;
import com.dashda.controllers.dto.VisitListInputDTO;
import com.dashda.data.entities.Account;
import com.dashda.data.entities.DoubleVisit;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.Product;
import com.dashda.data.entities.ProductVisit;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.User;
import com.dashda.data.entities.Visit;
import com.dashda.data.entities.VisitStatus;
import com.dashda.data.repositories.DoctorDao;
import com.dashda.data.repositories.DoubleVisitDao;
import com.dashda.data.repositories.EmployeeDao;
import com.dashda.data.repositories.ProductDao;
import com.dashda.data.repositories.ProductVisitDao;
import com.dashda.data.repositories.UserDao;
import com.dashda.data.repositories.VisitDao;
import com.dashda.enums.VisitStatusEnum;
import com.dashda.exception.ScheduleExceptionManager;
import com.dashda.exception.VisitServiceException;
import com.dashda.utilities.DateUtilities;


/**
 * @author mhanafy
 *
 */
@Service
public class VisitServiceImpl extends ServicesManager implements VisitService {

	private static final int SINGLE_VISIT = 0;

	private static final int DOUBLE_VISIT = 1;

	@Autowired
	UserDao userDao;
	
	@Autowired
	VisitDao visitDao;
	
	@Autowired
	DoubleVisitDao doubleVisitDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductVisitDao productVisitDao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	DoctorDao serviceProviderDao;
	

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
			visitDTO.setSpecialtyId(visit.getServiceProvider().getSpecialty().getId());
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
		employee = user.getEmployee();
		
		if(employee == null)
			throw new VisitServiceException(ERROR_CODE_1001);
		Account account = employee.getAccount();
		
		if(visitCompleteInput.getDoubleVisit() == DOUBLE_VISIT 
				&& visitCompleteInput.getManagerIds().isEmpty())
			throw new VisitServiceException(ERROR_CODE_1028);
		
		if(visitCompleteInput.getProductIds().isEmpty())
			throw new VisitServiceException(ERROR_CODE_1029);
		
		visit = visitDao.findUserVisitByIdAndNotComplete(visitCompleteInput.getId(), employee.getId());
		if(visit == null)
			throw new VisitServiceException(ERROR_CODE_1004);
		//check if approval in future 
		if(DateUtilities.compareTwoDates(new Date(), visit.getDatetime()) == 1)
			throw new VisitServiceException(ERROR_CODE_1019);
		
		visit.setComment(visitCompleteInput.getComment());
		visit.setVisitStatus(new VisitStatus(VisitStatusEnum.COMPLETE.getValue()));
		
		if(visitCompleteInput.getDoubleVisit() == SINGLE_VISIT)
			visit.setDoubleVisit(SINGLE_VISIT);
		else 
			visit.setDoubleVisit(DOUBLE_VISIT);
			
		visitDao.updateVisit(visit);
			
		
			//Create double visit entity 
		if(visitCompleteInput.getDoubleVisit() == DOUBLE_VISIT){
			for (Iterator managerIdsIt = visitCompleteInput.getManagerIds().iterator(); managerIdsIt.hasNext();) {
				int managerId = (int) managerIdsIt.next();
				DoubleVisit doubleVisit = new DoubleVisit();
				
				Employee manager = employeeDao.findEmployeeByID(managerId);
				if (manager == null) {
					throw new VisitServiceException(ERROR_CODE_1009);
				}
				
				doubleVisit.setManager(manager);
				doubleVisit.setEmployee(employee);
				doubleVisit.setVisit(visit);
				
				doubleVisitDao.addDoubleVisit(doubleVisit);
			}
		}
		
		//Add product visits
		for (Iterator productIt = visitCompleteInput.getProductIds().iterator(); productIt.hasNext();) {
			int productId = (int) productIt.next();
			ProductVisit productVisit = new ProductVisit();
			
			Product product = productDao.findProductByIdAndAccount(productId, account);
			
			if (product == null) 
				throw new VisitServiceException(ERROR_CODE_1020);
			
			productVisit.setProduct(product);
			productVisit.setVisit(visit);
			
			productVisitDao.addProductVisit(productVisit);
			
		}	
		
		
		//this.updateVisitStatus(user, visitCompleteInput.getId(), visitCompleteInput.getComment(), VisitStatusEnum.COMPLETE.getValue());
		
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
		visitDao.discardAllVisitsBeforeDate(executionDate);
		log.info("Engin Should Discard All Not Completed Visits Successfully");
		
	}

	@Override
	public AppResponse adhocVisit(String username, VisitAdhocVisitInputDTO visitAdhocVisitInput) throws VisitServiceException, ParseException {
		User user = userDao.findUserByUsername(username);
		
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new VisitServiceException(ERROR_CODE_1001);
		}
		
		//Get doctor entity
		ServiceProvider serviceProvider = serviceProviderDao.findDoctorById(visitAdhocVisitInput.getDoctorId());
		if (serviceProvider == null) {
			throw new VisitServiceException(ERROR_CODE_1020);
		}
		
		Date datetime = DateUtilities.convertToDate(visitAdhocVisitInput.getDate(), DateUtilities.DATE_FORMATE_PATTERN);
		//Validate visit date
		if(DateUtilities.compareTwoDates(new Date() 
				, datetime) == 1)
			throw new VisitServiceException(ERROR_CODE_1019);
		
		Visit visit = new Visit();
		visit.setDatetime(datetime);
		visit.setEmployeeByEmployeeId(employee);
		visit.setServiceProvider(serviceProvider);
		
		//====================================review
		visit.setComment(visitAdhocVisitInput.getComment());
		visit.setVisitStatus(new VisitStatus(VisitStatusEnum.COMPLETE.getValue()));
		
		if(visitAdhocVisitInput.getDoubleVisit() == SINGLE_VISIT)
			visit.setDoubleVisit(SINGLE_VISIT);
		else 
			visit.setDoubleVisit(DOUBLE_VISIT);
		
		visitDao.addVisit(visit);
		
		
		
		if(visitAdhocVisitInput.getDoubleVisit() == DOUBLE_VISIT) {
			//Create double visit entity 
						
			for (Iterator managerIdsIt = visitAdhocVisitInput.getManagerIds().iterator(); managerIdsIt.hasNext();) {
				int managerId = (int) managerIdsIt.next();
				DoubleVisit doubleVisit = new DoubleVisit();
				
				Employee manager = employeeDao.findEmployeeByID(managerId);
				if (manager == null) {
					throw new VisitServiceException(ERROR_CODE_1009);
				}
				
				doubleVisit.setManager(manager);
				doubleVisit.setEmployee(employee);
				doubleVisit.setVisit(visit);
				
				doubleVisitDao.addDoubleVisit(doubleVisit);
			}
		}
		
		//Add product visits
		for (Iterator productIt = visitAdhocVisitInput.getProductIds().iterator(); productIt.hasNext();) {
			int productId = (int) productIt.next();
			ProductVisit productVisit = new ProductVisit();
			
			Product product = productDao.findProductByIdAndAccount(productId, employee.getAccount());
			
			if (product == null) 
				throw new VisitServiceException(ERROR_CODE_1020);
			
			productVisit.setProduct(product);
			productVisit.setVisit(visit);
			
			productVisitDao.addProductVisit(productVisit);
			
		}
		
		
		//this.updateVisitStatus(user, visitCompleteInput.getId(), visitCompleteInput.getComment(), VisitStatusEnum.COMPLETE.getValue());

		//====================
		VisitAdhocVisitOutputDTO visitAdhocVisitOutputDTO 
			= new VisitAdhocVisitOutputDTO(visit.getId()
					, DateUtilities.dateFormate(visit.getDatetime())
					, visit.getServiceProvider().getId()); 
		
		return createResponse(visitAdhocVisitOutputDTO, "Adhoc Visit created successfully");
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
