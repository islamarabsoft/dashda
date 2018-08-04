/**
 * 
 */
package com.dashda.service.components;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.OkResponse;
import com.dashda.data.entities.Employee;
import com.dashda.data.entities.ServiceProvider;
import com.dashda.data.entities.User;
import com.dashda.data.repositories.UserDao;
import com.dashda.exception.AppExceptionHandler;


/**
 * @author mhanafy
 *
 */
@Transactional(rollbackFor = AppExceptionHandler.class)
public abstract class ServicesManager {
	
	
	protected static final String ERROR_CODE_1001 = "ERROR_CODE_1001";
	protected static final String ERROR_CODE_1002 = "ERROR_CODE_1002";
	protected static final String ERROR_CODE_1003 = "ERROR_CODE_1003";
	protected static final String ERROR_CODE_1004 = "ERROR_CODE_1004";
	protected static final String ERROR_CODE_1005 = "ERROR_CODE_1005";
	protected static final String ERROR_CODE_1006 = "ERROR_CODE_1006";
	protected static final String ERROR_CODE_1007 = "ERROR_CODE_1007";
	protected static final String ERROR_CODE_1008 = "ERROR_CODE_1008";
	protected static final String ERROR_CODE_1009 = "ERROR_CODE_1009";
	protected static final String ERROR_CODE_1010 = "ERROR_CODE_1010";
	protected static final String ERROR_CODE_1011 = "ERROR_CODE_1011";
	protected static final String ERROR_CODE_1012 = "ERROR_CODE_1012";
	protected static final String ERROR_CODE_1013 = "ERROR_CODE_1013";
	protected static final String ERROR_CODE_1014 = "ERROR_CODE_1014";
	protected static final String ERROR_CODE_1015 = "ERROR_CODE_1015";
	protected static final String ERROR_CODE_1016 = "ERROR_CODE_1016";
	protected static final String ERROR_CODE_1017 = "ERROR_CODE_1017";
	protected static final String ERROR_CODE_1018 = "ERROR_CODE_1018";
	protected static final String ERROR_CODE_1019 = "ERROR_CODE_1019";
	protected static final String ERROR_CODE_1020 = "ERROR_CODE_1020";
	protected static final String ERROR_CODE_1021 = "ERROR_CODE_1021";
	protected static final String ERROR_CODE_1022 = "ERROR_CODE_1022";
	protected static final String ERROR_CODE_1023 = "ERROR_CODE_1023";
	protected static final String ERROR_CODE_1024 = "ERROR_CODE_1024";
	protected static final String ERROR_CODE_1025 = "ERROR_CODE_1025";
	protected static final String ERROR_CODE_1026 = "ERROR_CODE_1026";
	protected static final String ERROR_CODE_1027 = "ERROR_CODE_1027";
	protected static final String ERROR_CODE_1028 = "ERROR_CODE_1028";
	protected static final String ERROR_CODE_1029 = "ERROR_CODE_1029";
	
	/**
	 * THIS ATTRIBUTE NOT USED
	 */
	@Autowired
	PropertySourcesPlaceholderConfigurer properties;
	
	@Autowired
	UserDao userDao;

	protected final Logger log = LoggerFactory.getLogger(ServicesManager.class);
	
	protected ModelMapper mapper = new ModelMapper();
	

	protected AppResponse emptyResponse(String message) {
		AppResponse appResponse = new AppResponse();
		
		appResponse.setStatus(202);
		appResponse.setMessage(message);
		
		return appResponse;
	}
	
	protected AppResponse createResponse(AbstractDTO abstractDTO, String message) {
		OkResponse okResponse = new OkResponse();
		okResponse.setStatus(201);
		okResponse.setMessage(message);
		okResponse.setData(abstractDTO);
		
		return okResponse;
	}
	
	protected AppResponse okResponse(AbstractDTO abstractDTO, String message) {
		
		OkResponse okResponse = new OkResponse();
		okResponse.setStatus(200);
		okResponse.setMessage(message);
		okResponse.setData(abstractDTO);
		
		return okResponse;
	}
	
	protected AppResponse okListResponse(List<AbstractDTO> abstractDTOs, String message) {
		
		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(200);
		postResponse.setMessage(message);
		postResponse.setData(abstractDTOs);
		
		return postResponse;
		
	}
	
	protected AppResponse okListResponse(List<AbstractDTO> abstractDTOs) {
		
		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(200);
		postResponse.setMessage("List Size is : " + abstractDTOs.size());
		postResponse.setData(abstractDTOs);
		
		return postResponse;
		
	}
	
	private AppResponse handleResponse(int statusCode, List<AbstractDTO> data, String message) {
		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(statusCode);
		postResponse.setMessage(message);
		postResponse.setData(data);
		
		return postResponse;
	}
	
	protected Employee getEmployee(String userName) throws AppExceptionHandler {
		User user = userDao.findUserByUsername(userName);
		Employee employee = user.getEmployee();
		if (employee == null) {
			throw new AppExceptionHandler(ERROR_CODE_1001);
		}
		return employee;
	}
	
	
	protected User getUser(String userName) {
		User user = userDao.findUserByUsername(userName);
		return user;
	}
	
	protected String getServiceProviderName(ServiceProvider provider) {
		
		return provider.getFirstName() + " "
				+ provider.getLastName();
	}
	
	protected String getServiceProviderType(ServiceProvider provider) {
		
		return provider.getServiceProviderType().getName();
	}
}
