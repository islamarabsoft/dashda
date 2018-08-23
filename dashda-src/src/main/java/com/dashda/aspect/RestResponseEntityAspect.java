/**
 * 
 */
package com.dashda.aspect;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.dashda.annotation.RestResponseEntity;
import com.dashda.controllers.dto.AbstractDTO;
import com.dashda.controllers.dto.AppResponse;
import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.OkResponse;

/**
 * @author mohamed.hanfy
 *
 */
@Aspect
@Component
public class RestResponseEntityAspect {

	HttpStatus httpStatus = HttpStatus.OK;
	
	@Pointcut(value = "execution (@com.dashda.annotation.RestResponseEntity  * *.*(..))") 
    public void restResponseEnityPC() {  
	} 
	
	@Around("restResponseEnityPC() && @annotation(restResponseEntity)")
	public ResponseEntity restResponseEnity(ProceedingJoinPoint joinPoint, final RestResponseEntity restResponseEntity) throws Throwable {
	
		switch (restResponseEntity.returnType())
	    {
	      case CREATE:
	    	  return create(joinPoint, restResponseEntity);
	      case DELETE:
	    	  return delete(joinPoint, restResponseEntity);
	      case LIST:
	    	  return returnList(joinPoint, restResponseEntity);
	      case UPDATE:
	    	  return update(joinPoint, restResponseEntity);
	    	  default: return null;
	    }
		
	}
	
	
	private ResponseEntity update(ProceedingJoinPoint joinPoint, RestResponseEntity restResponseEntity) throws Throwable {
		OkResponse okResponse = new OkResponse();
		
		httpStatus = HttpStatus.ACCEPTED;
		okResponse.setStatus(httpStatus);
		okResponse.setMessage(restResponseEntity.message());
		okResponse.setData((AbstractDTO)joinPoint.proceed());
		
		return new ResponseEntity (okResponse, httpStatus);
	}

	private ResponseEntity delete(ProceedingJoinPoint joinPoint, RestResponseEntity restResponseEntity) throws Throwable {
		
		httpStatus = HttpStatus.ACCEPTED;
		AppResponse appResponse = new AppResponse();
		
		appResponse.setStatus(httpStatus);
		appResponse.setMessage(restResponseEntity.message());
		joinPoint.proceed();
		
		return new ResponseEntity (appResponse, httpStatus);
	}

	private ResponseEntity returnList(ProceedingJoinPoint joinPoint, final RestResponseEntity restResponseEntity) throws Throwable {
		
		httpStatus = HttpStatus.OK;
		List abstractDTOs = (List)joinPoint.proceed();
		
		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(httpStatus);
		int listSize = 0;
		if(abstractDTOs != null)
			listSize = abstractDTOs.size();
		postResponse.setMessage("List Size is : " + listSize);
		postResponse.setData(abstractDTOs);
		
		return new ResponseEntity (postResponse, httpStatus);
	}
	
	
	private ResponseEntity create(ProceedingJoinPoint joinPoint, final RestResponseEntity restResponseEntity) throws Throwable {
		
		httpStatus = HttpStatus.CREATED;
		
		OkResponse okResponse = new OkResponse();
		okResponse.setStatus(httpStatus);
		okResponse.setMessage(restResponseEntity.message());
		okResponse.setData((AbstractDTO)joinPoint.proceed());
		
		return new ResponseEntity (okResponse, httpStatus);
	}
}
