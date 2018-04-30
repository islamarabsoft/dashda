/**
 * 
 */
package com.dashda.exception;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dashda.utilities.Messages;
import com.dashda.utilities.SpringContext;


/**
 * @author mhanafy
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final String GENERIC_MSG = "INTERNAL_ERROR";
	private static final String ACCESS_DENIED = "AccessDenied";
	
	protected final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> exceptionHandler(Exception ex) {
		Messages messages = (Messages)SpringContext.getApplicationContext().getBean("messages");
		ApiError error = new ApiError();
		Fault fault = new Fault();
		if(ex instanceof AppExceptionHandler) {
			fault.setArErrorMessage(((AppExceptionHandler) ex).getArErrorMessage());
			fault.setEnErrorMessage(((AppExceptionHandler) ex).getEnErrorMessage());
			fault.setErrorCode(((AppExceptionHandler) ex).getErrorCode());
		}else if(ex instanceof AccessDeniedException){
			fault.setArErrorMessage(messages.get(ACCESS_DENIED, new Locale("ar")));
			fault.setEnErrorMessage(messages.get(ACCESS_DENIED, Locale.US));
			fault.setErrorCode(ACCESS_DENIED);
		}else {
			fault.setArErrorMessage(messages.get(GENERIC_MSG, new Locale("ar")));
			fault.setEnErrorMessage(messages.get(GENERIC_MSG, Locale.US));
			fault.setErrorCode(GENERIC_MSG);
		}
		
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setDebugMessage(ex.getLocalizedMessage());
		error.setMessage(ex.getMessage());
		error.setFault(fault);
		
		log.error("Ops! :::::: ", ex);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
