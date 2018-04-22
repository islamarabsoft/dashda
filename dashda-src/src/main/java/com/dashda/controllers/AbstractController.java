/**
 * 
 */
package com.dashda.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mhanafy
 *
 */
@CrossOrigin
public abstract class AbstractController {

	protected final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	protected ObjectMapper jsonObjectmapper = new ObjectMapper();
	
	protected ResponseEntity returnResponseEntityOk(Object o){
		return new ResponseEntity (o, HttpStatus.OK);
	}
	
	protected ResponseEntity returnResponseEntityCreated(Object o){
		return new ResponseEntity (o, HttpStatus.CREATED);
	}
	
	protected ResponseEntity returnResponseEntityAccepted(Object o){
		return new ResponseEntity (o, HttpStatus.ACCEPTED);
	}
}
