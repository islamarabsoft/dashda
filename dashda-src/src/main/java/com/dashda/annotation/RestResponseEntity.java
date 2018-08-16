/**
 * 
 */
package com.dashda.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.HttpStatus;

import com.dashda.enums.ReturnType;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author mohamed.hanfy
 *
 */
public @interface RestResponseEntity {

	HttpStatus status() default HttpStatus.OK;
	ReturnType returnType() default ReturnType.LIST;
	String message() default "Action Done Successfully";
}
