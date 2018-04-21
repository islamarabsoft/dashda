/**
 * 
 */
package com.dashda.exception;

import static org.hamcrest.CoreMatchers.instanceOf;

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

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> exceptionHandler(Exception ex) {
		ApiError error = new ApiError();
		
		if(ex instanceof DashdaExceptionHandling) {
			error.setArErrorMessage(((DashdaExceptionHandling) ex).getArErrorMessage());
			error.setEnErrorMessage(((DashdaExceptionHandling) ex).getEnErrorMessage());
			error.setErrorCode(((DashdaExceptionHandling) ex).getErrorCode());
		}
		
		error.setDebugMessage(ex.getLocalizedMessage());
		error.setMessage(ex.getMessage());
		
		ex.printStackTrace();
		return new ResponseEntity(error, HttpStatus.EXPECTATION_FAILED);
	}
}
