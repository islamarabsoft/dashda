/**
 * 
 */
package com.dashda.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mhanafy
 *
 */
@RestController
@RequestMapping("/manager")
public class ManagerControllers {

	protected final Logger log = LoggerFactory.getLogger(ManagerControllers.class);
	
	@RequestMapping("/manager-info")
	public String customerInfo() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		log.info("Manager");
		return "Manager";
	}
}
