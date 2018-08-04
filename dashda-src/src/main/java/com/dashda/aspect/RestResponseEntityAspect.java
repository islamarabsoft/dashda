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
import com.dashda.controllers.dto.ListResponse;

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
	
		List abstractDTOs = (List)joinPoint.proceed();

		System.out.println();
		ListResponse postResponse = new ListResponse();
		postResponse.setStatus(restResponseEntity.status().value());
		postResponse.setMessage("List Size is : " + abstractDTOs.size());
		postResponse.setData(abstractDTOs);
		
		return new ResponseEntity (postResponse, restResponseEntity.status());
	}
}
