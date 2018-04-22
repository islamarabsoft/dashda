/**
 * 
 */
package com.dashda.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * @author mhanafy
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	protected final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> exceptionHandler(Exception ex) {
		ApiError error = new ApiError();
		Fault fault = new Fault();
		if(ex instanceof DashdaExceptionHandling) {
			fault.setArErrorMessage(((DashdaExceptionHandling) ex).getArErrorMessage());
			fault.setEnErrorMessage(((DashdaExceptionHandling) ex).getEnErrorMessage());
			fault.setErrorCode(((DashdaExceptionHandling) ex).getErrorCode());
		}else {
			fault.setArErrorMessage("Ar Generic MSG");
			fault.setEnErrorMessage("En Generic MSG");
			fault.setErrorCode("001");
		}
		
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setDebugMessage(ex.getLocalizedMessage());
		error.setMessage(ex.getMessage());
		error.setFault(fault);
		
		log.error("Ops! :::::: ", ex);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
