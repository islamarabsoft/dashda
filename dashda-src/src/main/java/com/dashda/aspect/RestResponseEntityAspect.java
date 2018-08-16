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
import com.dashda.controllers.dto.ListResponse;
import com.dashda.controllers.dto.OkResponse;

/**
 * @author mohamed.hanfy
 *
 */
@Aspect
@Component
public class RestResponseEntityAspect {

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
	      case LIST:
	    	  return returnList(joinPoint, restResponseEntity);
	      case UPDATE:
	    	  default: return null;
	    }
	    	  
		
	}
	
	
	private ResponseEntity returnList(ProceedingJoinPoint joinPoint, final RestResponseEntity restResponseEntity) throws Throwable {
		
		List abstractDTOs = (List)joinPoint.proceed();

		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(200);
		int listSize = 0;
		if(abstractDTOs != null)
			listSize = abstractDTOs.size();
		postResponse.setMessage("List Size is : " + listSize);
		postResponse.setData(abstractDTOs);
		
		return new ResponseEntity (postResponse, HttpStatus.OK);
	}
	
	
	private ResponseEntity create(ProceedingJoinPoint joinPoint, final RestResponseEntity restResponseEntity) throws Throwable {
		
		OkResponse okResponse = new OkResponse();
		okResponse.setStatus(201);
		okResponse.setMessage(restResponseEntity.message());
		okResponse.setData((AbstractDTO)joinPoint.proceed());
		
		return new ResponseEntity (okResponse, HttpStatus.CREATED);
	}
}
