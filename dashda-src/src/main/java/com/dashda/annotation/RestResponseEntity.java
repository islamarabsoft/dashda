/**
 * 
 */
package com.dashda.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.HttpStatus;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author mohamed.hanfy
 *
 */
public @interface RestResponseEntity {

	HttpStatus status() default HttpStatus.OK;
}
